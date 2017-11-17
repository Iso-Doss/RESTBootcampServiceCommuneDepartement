package com.bootcamp.service.crud;

import com.bootcamp.AppConstants;
import com.bootcamp.entities.Departement;
import com.bootcamp.jpa.DepartementRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DepartementCRUD implements AppConstants {

    public static boolean create(Departement departement) {
        DepartementRepository departementRepository = new DepartementRepository(AppConstants.PERSISTENCE_UNIT);
        try {
            return departementRepository.create(departement);

        } catch (SQLException ex) {

            Logger.getLogger(DepartementCRUD.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean update(Departement departement) {
        DepartementRepository departementRepository = new DepartementRepository(AppConstants.PERSISTENCE_UNIT);
        try {
            return departementRepository.update(departement);
        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            Logger.getLogger(DepartementCRUD.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;

    }

    public static Departement read(String name) {
        DepartementRepository departementRepository = new DepartementRepository(AppConstants.PERSISTENCE_UNIT);
        try {
            return departementRepository.findByPropertyUnique("nom", name);

        } catch (SQLException e) {
            Logger.getLogger(DepartementCRUD.class.getName()).log(Level.SEVERE, null, e);

        }
        return null;
    }

    public static List<Departement> readAll(String pays) {
        DepartementRepository departementRepository = new DepartementRepository(AppConstants.PERSISTENCE_UNIT);
        try {
            return departementRepository.findByProperty("pays", pays);
        } catch (SQLException e) {
            Logger.getLogger(DepartementCRUD.class.getName()).log(Level.SEVERE, null, e);

        }

        return null;

    }

    public static boolean delete(Departement departement) {
        DepartementRepository departementRepository = new DepartementRepository(AppConstants.PERSISTENCE_UNIT);
        try {
            departementRepository.delete(departement);
            return true;
        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            Logger.getLogger(DepartementCRUD.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static Departement read(Long id) {
        DepartementRepository departementRepository = new DepartementRepository(AppConstants.PERSISTENCE_UNIT);
        try {
            return departementRepository.findByPropertyUnique("id", id);

        } catch (SQLException ex) {
            Logger.getLogger(DepartementCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static List<Departement> readAll() {
        DepartementRepository departementRepository = new DepartementRepository(AppConstants.PERSISTENCE_UNIT);
        try {
            return departementRepository.findAll();
        } catch (SQLException ex) {
            Logger.getLogger(DepartementCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
