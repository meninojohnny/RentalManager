package com.johnny.manager;

import com.johnny.model.Adress;
import com.johnny.model.Apartment;
import com.johnny.service.ApartmentService;
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
public class ApartmentManager implements Serializable {
    
    @EJB
    private ApartmentService apartmentService;
    
    private Apartment apartment;
    
    private List<Apartment> apartments;
    
    private List<Apartment> apartmentsAvailable;
    
    @PostConstruct
    public void instance() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String edit = params.get("edit");
        String view = params.get("view");

        if (edit != null) {
            apartment = apartmentService.find(Long.valueOf(edit));
        } else if (view != null) {
            apartment = apartmentService.find(Long.valueOf(view));
        } else {
            apartment = new Apartment();
            apartment.setAdress(new Adress());
        }
        
        this.findByAll();
        findAvailable();
    }
    
    public void save() {
        this.apartmentService.save(apartment);
        Message.msg("Operação realizada com sucesso!");
        instance();
    }
    
    public void update() {
        this.apartmentService.update(apartment);
    }
    
    public void remove() {
        this.apartmentService.remove(apartment);        
    }
    
    public void findAll() {
        apartments = this.apartmentService.findAll();
    }
    
    public void findByAll() {
        apartments = this.apartmentService.findByAll(apartment);
    }
    
    public void findAvailable() {
        apartmentsAvailable = this.apartmentService.findAvailable();
    }
    
    public String isAvailableFormated(Boolean isAvaliable) {
        return this.apartmentService.isAvailableFormated(isAvaliable);
    }

    public ApartmentService getApartmentService() {
        return apartmentService;
    }

    public void setApartmentService(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public List<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(List<Apartment> apartments) {
        this.apartments = apartments;
    }

    public List<Apartment> getApartmentsAvailable() {
        return apartmentsAvailable;
    }

    public void setApartmentsAvailable(List<Apartment> apartmentsAvailable) {
        this.apartmentsAvailable = apartmentsAvailable;
    }
    
}