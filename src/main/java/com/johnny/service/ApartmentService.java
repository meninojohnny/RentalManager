package com.johnny.service;

import com.johnny.generic.GenericService;
import com.johnny.model.Apartment;
import com.johnny.utilitarie.Message;
import com.johnny.utilitarie.PageUtil;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

@Stateless
public class ApartmentService extends GenericService<Apartment> {

    public ApartmentService() {
        super(Apartment.class);
    }
    
    @Override
    public void save(Apartment apartment) {
        apartment.setIsAvailable(Boolean.TRUE);
        this.getEntityManager().persist(apartment);
    }

    public void update(Apartment apartment) {
        apartment.setUpdateDate(new Date());
        this.getEntityManager().merge(apartment);
        Message.msg("Operação realizada com sucesso!");
        PageUtil.changePage("apartmentManager.xhtml");
    }

    public void remove(Apartment apartment) {
        if (apartment.getIsAvailable() == true) {
            apartment.setActive(Boolean.FALSE);
            this.getEntityManager().merge(apartment);
            PageUtil.changePage("apartmentManager.xhtml");
        } else {
            Message.msg("Esse apartamento está ativo em um contrato!");
        }
    }

    public List<Apartment> findAvailable() {
        String sql = "SELECT a FROM Apartment a WHERE a.isAvailable = true AND a.active = true ORDER BY a.registerDate DESC";
        return this.getEntityManager().createQuery(sql).getResultList();
    }

    public List<Apartment> findByAll(Apartment apartment) {
        String sql = "SELECT a FROM Apartment a WHERE ";

        if (apartment.getNumber() != null && !apartment.getNumber().equals("")) {
            sql += "a.number LIKE :number AND ";
        }

        if (apartment.getQuantityRooms() != null && !apartment.getQuantityRooms().equals("")) {
            sql += "a.quantityRooms LIKE :quantityRooms AND ";
        }

        if (apartment.getPrice() != null && !apartment.getPrice().equals("")) {
            sql += "a.price LIKE :price AND ";
        }

        if (apartment.getAdress().getNeighborhood() != null && !apartment.getAdress().getNeighborhood().equals("")) {
            sql += "LOWER(a.adress.neighborhood) LIKE LOWER(:neighborhood) AND ";
        }
        
        if (apartment.getIsAvailable() != null) {
           sql += "a.isAvailable = :isAvailable AND ";
        }

        sql += "a.active = true ORDER BY a.registerDate DESC";

        Query query = this.getEntityManager().createQuery(sql);

        if (apartment.getNumber() != null && !apartment.getNumber().equals("")) {
            query.setParameter("number", "%" + apartment.getNumber() + "%");
        }

        if (apartment.getQuantityRooms() != null && !apartment.getQuantityRooms().equals("")) {
            query.setParameter("quantityRooms", "%" + apartment.getQuantityRooms() + "%");
        }

        if (apartment.getPrice() != null && !apartment.getPrice().equals("")) {
            query.setParameter("price", apartment.getPrice());
        }

        if (apartment.getAdress().getNeighborhood() != null && !apartment.getAdress().getNeighborhood().equals("")) {
            query.setParameter("neighborhood", "%" + apartment.getAdress().getNeighborhood() + "%");
        }
        
        if (apartment.getIsAvailable()!= null) {
            query.setParameter("isAvailable", apartment.getIsAvailable());
        }

        return query.getResultList();
    }
    
    public String isAvailableFormated(Boolean isAvaliable) {
        if (isAvaliable == true) {
            return "Sim";
        } else {
            return "Não";
        }
    }
}
