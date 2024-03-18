package com.johnny.manager;

import com.johnny.model.Contract;
import com.johnny.service.ContractService;
import com.johnny.utilitarie.DateUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ContractManager implements Serializable {
    
    @EJB
    private ContractService contractService;
    
    private Contract contract;
    
    private List<Contract> contracts;
    
    @PostConstruct
    public void instance() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String edit = params.get("edit");
        String view = params.get("view");

        if (edit != null) {
            contract = contractService.find(Long.valueOf(edit));
        } else if (view != null) {
            contract = contractService.find(Long.valueOf(view));
        } else {
            contract = new Contract();
        }
        
        this.contractService.verifyStatus();
        findByAll();
    }
    
    public void save() {
        this.contractService.save(contract);
        instance();
    }
    
    public void update() {
        this.contractService.update(contract);
    }
    
    public void remove() {
        this.contractService.remove(contract);
    }
    
    public void findAll() {
        contracts = this.contractService.findAll();
    }
    
    public void findByAll() {
        contracts = this.contractService.findByAll(contract);
    }
    
    public void makePayment(Long id) {
        this.contractService.makePayment(id);
    }

    public ContractService getContractService() {
        return contractService;
    }

    public void setContractService(ContractService contractService) {
        this.contractService = contractService;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public List<Contract> getConstracts() {
        return contracts;
    }

    public void setConstracts(List<Contract> constracts) {
        this.contracts = constracts;
    }
    
    public String dateFormated(Date date) {
        return DateUtil.dateToString(date);
    }
    
}