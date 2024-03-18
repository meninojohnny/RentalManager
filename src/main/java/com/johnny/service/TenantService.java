package com.johnny.service;

import com.johnny.generic.GenericService;
import com.johnny.model.Tenant;
import com.johnny.utilitarie.Message;
import com.johnny.utilitarie.PageUtil;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

@Stateless
public class TenantService extends GenericService<Tenant> {
    
    public TenantService() {
        super(Tenant.class);
    }
    
    public void update(Tenant tenant) {
        tenant.setUpdateDate(new Date());
        this.getEntityManager().merge(tenant);
        Message.msg("Operação realizada com sucesso!");
        PageUtil.changePage("tenantManager.xhtml");
    }
    
    public void remove(Tenant tenant) {
        if (tenant.getApartment() == null) {
            tenant.setActive(Boolean.FALSE);
            this.getEntityManager().merge(tenant);
            PageUtil.changePage("tenantManager.xhtml");
        } else {
            Message.msg("Esse inquilino está ativo em um contrato!");
        }
    }
    
    public List<Tenant> findAvailable() {
        String sql = "SELECT t FROM Tenant t WHERE t.apartment IS null AND t.active = true ORDER BY t.registerDate DESC";
        return this.getEntityManager().createQuery(sql).getResultList();
    }
    
    public List<Tenant> findByAll(Tenant tenant){
        String sql = "SELECT t FROM Tenant t WHERE ";
        
        if (tenant.getName() != null && !tenant.getName().equals("")) {
            sql += " LOWER(t.name) LIKE LOWER(:name) AND ";
        }
        
        if (tenant.getPhone()!= null && !tenant.getPhone().equals("")) {
            sql += "t.phone LIKE :phone AND ";
        }
        
        if (tenant.getGender()!= null) {
            sql += "t.gender LIKE LOWER(:gender) AND ";
        }
        
        sql += "t.active = true ORDER BY t.registerDate DESC";
        
        Query query = this.getEntityManager().createQuery(sql);
        
        if (tenant.getName() != null && !tenant.getName().equals("")) {
            query.setParameter("name", "%" + tenant.getName() + "%");
        }
        
        if (tenant.getPhone()!= null && !tenant.getPhone().equals("")) {
            query.setParameter("phone", "%" + tenant.getPhone()+ "%");
        }
        
        if (tenant.getGender()!= null) {
            query.setParameter("gender", "%" + tenant.getGender()+ "%");
        }
        
        return query.getResultList();
    }

}