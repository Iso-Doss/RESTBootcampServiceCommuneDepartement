//package com.bootcamp;
//
//import javax.persistence.*;
//import org.testng.annotations.Test;
//import java.util.*;
//
///**
// *
// * @author Iso-Doss
// */
//public class GenerateTableTest {
//
//    /**
//     * Méthode pour generer la structure de la base de donnee MySql
//     */
//    @Test
//    public void generateTablesMySql() {
//        Persistence.generateSchema("tpJpa", new Properties());
//    }
//
//    /**
//     * Méthode pour generer la structure de la base de donnee Derby
//     *
//     */
//    @Test
//    public void generateTablesDerby() {
//        Persistence.generateSchema("com.bootcamp_TpJPA", new Properties());
//    }
//
//    /**
//     * Méthode pour generer la structure de la base de donnee MySql Service
//     */
//    @Test
//    public void generateTablesMySqlService() {
//        Persistence.generateSchema("tpservice-mysql", new Properties());
//    }
//
//    /**
//     * Méthode pour generer la structure de la base de donnee Derby Service
//     *
//     */
//    @Test
//    public void generateTablesDerbyService() {
//        Persistence.generateSchema("tpservice-derby", new Properties());
//    }
//}
