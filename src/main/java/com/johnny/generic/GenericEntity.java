package com.johnny.generic;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class GenericEntity implements Serializable {
    
    private Boolean active;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    
    public GenericEntity() {
        active = true;
        registerDate = new Date();
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
  
}
