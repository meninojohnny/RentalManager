package com.johnny.manager;

import com.johnny.model.Tenant;
import com.johnny.service.TenantService;
import com.johnny.utilitarie.Message;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class TenantManager implements Serializable {
    
    @EJB
    private TenantService tenantService;
    
    private Tenant tenant;
    
    private List<Tenant> tenants;
    private List<Tenant> tenantsAvailable;
    
    @PostConstruct
    public void instance() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String edit = params.get("edit");
        String view = params.get("view");

        if (edit != null) {
            tenant = tenantService.find(Long.valueOf(edit));
        } else if (view != null) {
            tenant = tenantService.find(Long.valueOf(view));
        } else {
            tenant = new Tenant();
        }
        
        this.findByAll();
        findAvailable();
    }
    
    public void save() {
        this.tenantService.save(tenant);
        instance();
        Message.msg("Operação realizada com sucesso!");
    }
    
    public void update() {
        this.tenantService.update(tenant);
    }
    
    public void remove() {
        this.tenantService.remove(tenant);
    }
    
    public void findAll() {
        tenants = this.tenantService.findAll();
    }
    
    public void findByAll() {
        tenants = this.tenantService.findByAll(tenant);
    }
    
    public void findAvailable() {
        tenantsAvailable = this.tenantService.findAvailable();
    }

    public TenantService getTenantService() {
        return tenantService;
    }

    public void setTenantService(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public List<Tenant> getTenants() {
        return tenants;
    }

    public void setTenants(List<Tenant> tenants) {
        this.tenants = tenants;
    }

    public List<Tenant> getTenantsAvailable() {
        return tenantsAvailable;
    }

    public void setTenantsAvailable(List<Tenant> tenantsAvailable) {
        this.tenantsAvailable = tenantsAvailable;
    }
    
}
