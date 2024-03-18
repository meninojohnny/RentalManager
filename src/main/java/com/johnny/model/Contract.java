package com.johnny.model;

import com.johnny.generic.GenericEntity;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Contract extends GenericEntity {
    
    @Id
    @SequenceGenerator(name = "seq_contract", sequenceName = "seq_contract", initialValue = 1)
    @GeneratedValue(generator = "seq_contract", strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @OneToOne
    private Tenant tenant;
    
    @OneToOne
    private Apartment apartment;
    
    @Temporal(TemporalType.DATE)
    private Date payday;
    
    private String status;
    
    public Contract() {
        tenant = new Tenant();
        apartment = new Apartment();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public Date getPayday() {
        return this.payday;
    }

    public void setPayday(Date payday) {
        this.payday = payday;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
