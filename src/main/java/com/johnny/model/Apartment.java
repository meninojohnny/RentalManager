package com.johnny.model;

import com.johnny.generic.GenericEntity;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Apartment extends GenericEntity {
    
    @Id
    @SequenceGenerator(name = "seq_apartment", sequenceName = "seq_apartment", initialValue = 1)
    @GeneratedValue(generator = "seq_apartment", strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String number;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Adress adress;
    
    private Boolean isAvailable;
    
    private String quantityRooms;
    
    private String description;
    
    private String price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantityRooms() {
        return quantityRooms;
    }

    public void setQuantityRooms(String quantityRooms) {
        this.quantityRooms = quantityRooms;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    } 
    
}
