package com.bootcamp;

import java.util.Properties;
import javax.persistence.*;
import org.testng.annotations.Test;

/**
 *
 * @author Iso-Doss
 */
public class GenerateTableTest {

    /**
     * Méthode pour generer la structure de la base de donnee MySql
     */
    @Test
    public void generateTablesMySql() {
        Persistence.createEntityManagerFactory(AppConstants.PERSISTENCE_UNIT, new Properties() {
        });
    }
}
