package com.johnny.service;

import com.johnny.generic.GenericService;
import com.johnny.model.Contract;
import com.johnny.utilitarie.Message;
import com.johnny.utilitarie.PageUtil;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

@Stateless
public class ContractService extends GenericService<Contract> {

    public ContractService() {
        super(Contract.class);
    }

    public void update(Contract contract) {
        contract.setUpdateDate(new Date());
        this.getEntityManager().merge(contract);
        //Message.msg("Operação realizada com sucesso!");
        PageUtil.changePage("contractManager.xhtml");
    }

    public void remove(Contract contract) {
        contract.getTenant().setApartment(null);
        this.getEntityManager().merge(contract.getTenant());

        contract.getApartment().setIsAvailable(Boolean.TRUE);
        this.getEntityManager().merge(contract.getApartment());

        contract.setActive(Boolean.FALSE);
        this.getEntityManager().merge(contract);
        PageUtil.changePage("contractManager.xhtml");
    }

    @Override
    public void save(Contract contract) {
        contract.getApartment().setIsAvailable(Boolean.FALSE);
        this.getEntityManager().merge(contract.getApartment());

        contract.getTenant().setApartment(contract.getApartment());
        this.getEntityManager().merge(contract.getTenant());

        contract.setStatus("Ativo");
        this.getEntityManager().persist(contract);
        Message.msg("Operação realizada com sucesso!");
        //PageUtil.changePage("contract.xhtml");
    }

    public List<Contract> findByAll(Contract contract) {
        String sql = "SELECT c FROM Contract c WHERE ";

        if (contract.getTenant().getName() != null && !contract.getTenant().getName().equals("")) {
            sql += " LOWER(c.tenant.name) LIKE LOWER(:name) AND ";
        }

        if (contract.getApartment().getNumber() != null && !contract.getApartment().getNumber().equals("")) {
            sql += "c.apartment.number LIKE :number AND ";
        }
        
        if (contract.getStatus() != null && !contract.getStatus().equals("")) {
            sql += "c.status LIKE :status AND ";
        }

        sql += "c.active = true ORDER BY c.registerDate DESC";

        Query query = this.getEntityManager().createQuery(sql);

        if (contract.getTenant().getName() != null && !contract.getTenant().getName().equals("")) {
            query.setParameter("name", "%" + contract.getTenant().getName() + "%");
        }

        if (contract.getApartment().getNumber() != null && !contract.getApartment().getNumber().equals("")) {
            query.setParameter("number", "%" + contract.getApartment().getNumber() + "%");
        }
        
        if (contract.getStatus() != null && !contract.getStatus().equals("")) {
            query.setParameter("status", "%" + contract.getStatus() + "%");
        }

        return query.getResultList();
    }
    
    public void verifyStatus() {
        List<Contract> contracts = this.findAll();
        contracts.forEach((contract) -> {
            if (contract.getPayday().before(new Date())) {
                contract.setStatus("Pendente");
                this.getEntityManager().merge(contract);
            }
        });
    }

    public void makePayment(Long id) {
        Contract contract = this.getEntityManager().find(Contract.class, id);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(contract.getPayday());
        calendar.add(Calendar.MONTH, 1);
        contract.setPayday(calendar.getTime());
        contract.setStatus("Ativo");
        this.getEntityManager().merge(contract);
        PageUtil.changePage("contractManager.xhtml");
    }
}
