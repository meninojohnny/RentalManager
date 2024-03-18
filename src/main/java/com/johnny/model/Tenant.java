package com.johnny.model;

import com.johnny.enums.GenderType;
import com.johnny.generic.GenericEntity;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Tenant extends GenericEntity {
    
    @Id
    @SequenceGenerator(name = "seq_tenant", sequenceName = "seq_tenant", initialValue = 1)
    @GeneratedValue(generator = "seq_tenant", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    
    private String phone;
    
    @Enumerated(EnumType.STRING)
    private GenderType gender;
    
    @OneToOne
    private Apartment apartment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }
    
    public String getGender() {
        if (this.gender == GenderType.MASCULINE) {
            return "Masculino";
        } else if (this.gender == GenderType.FEMININE) {
            return "Feminino";
        } else {
            return null;
        }
    }

    public void setGender(String gender) {
        if (gender.equals("Masculino")) {
            this.gender = GenderType.MASCULINE;
        } else if (gender.equals("Feminino")) {
            this.gender = GenderType.FEMININE;
        }
    }
    
}
