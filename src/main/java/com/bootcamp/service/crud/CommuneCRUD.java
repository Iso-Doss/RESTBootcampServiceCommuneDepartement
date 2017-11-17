package com.bootcamp.service.crud;

import com.bootcamp.AppConstants;
import com.bootcamp.entities.Commune;
import com.bootcamp.jpa.CommuneRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommuneCRUD implements AppConstants {

    public static boolean create(Commune commune) {
        CommuneRepository communeRepository = new CommuneRepository(AppConstants.PERSISTENCE_UNIT);
        try {
            return communeRepository.create(commune);

        } catch (SQLException ex) {

            Logger.getLogger(CommuneCRUD.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static boolean update(Commune commune) {
        CommuneRepository communeRepository = new CommuneRepository(AppConstants.PERSISTENCE_UNIT);
        try {
            return communeRepository.update(commune);
        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            Logger.getLogger(CommuneCRUD.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;

    }

    public static Commune read(String name) {
        CommuneRepository communeRepository = new CommuneRepository(AppConstants.PERSISTENCE_UNIT);
        try {
            return communeRepository.findByPropertyUnique("nom", name);

        } catch (SQLException e) {
            Logger.getLogger(CommuneCRUD.class.getName()).log(Level.SEVERE, null, e);

        }
        return null;
    }

    public static List<Commune> readAll(String pays) {
        CommuneRepository communeRepository = new CommuneRepository(AppConstants.PERSISTENCE_UNIT);
        try {
            return communeRepository.findByProperty("pays", pays);
        } catch (SQLException e) {
            Logger.getLogger(CommuneCRUD.class.getName()).log(Level.SEVERE, null, e);

        }

        return null;

    }

    public static boolean delete(Commune commune) {
        CommuneRepository communeRepository = new CommuneRepository(AppConstants.PERSISTENCE_UNIT);
        try {
            communeRepository.delete(commune);
            return true;
        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            Logger.getLogger(CommuneCRUD.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public static Commune read(Long id) {
        CommuneRepository communeRepository = new CommuneRepository(AppConstants.PERSISTENCE_UNIT);
        try {
            return communeRepository.findByPropertyUnique("id", id);

        } catch (SQLException ex) {
            Logger.getLogger(CommuneCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static List<Commune> readAll() {
        CommuneRepository communeRepository = new CommuneRepository(AppConstants.PERSISTENCE_UNIT);
        try {
            return communeRepository.findAll();
        } catch (SQLException ex) {
            Logger.getLogger(CommuneCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
