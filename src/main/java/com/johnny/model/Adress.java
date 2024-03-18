package com.johnny.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Adress implements Serializable {
    
    @Id
    @SequenceGenerator(name = "seq_adress", sequenceName = "seq_adress", initialValue = 1)
    @GeneratedValue(generator = "seq_adress", strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String street;
    
    private String neighborhood;
    
    private String cep;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    
    
    
}
