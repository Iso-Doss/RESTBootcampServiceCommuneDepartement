///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.bootcamp.rest.controllers;
//
//import com.bootcamp.jpa.repositories.*;
//import com.bootcamp.jpa.entities.*;
//import java.sql.SQLException;
//import javax.ws.rs.core.*;
//import java.util.List;
//import javax.ws.rs.*;
//
///**
// *
// * @author Iso-Doss
// */
//@Path("/projets")
//public class ProjetRestController {
//
//    private ProjetRepository derby = new ProjetRepository("com.bootcamp_TpJPA");
//    private ProjetRepository mysql = new ProjetRepository("tpJpa");
//
//    /**
//     *
//     * @return @throws SQLException
//     */
//    @GET
//    @Path("/")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getList() throws SQLException {
//        List<Projet> projets = getDerby().findAll();
//        if (projets == null) {
//            return Response.status(404).entity(projets).build();
//        } else {
//            return Response.status(200).entity(projets).build();
//        }
//    }
//
//    /**
//     *
//     * @param id
//     * @return
//     * @throws SQLException
//     */
//    @GET
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getListById(@PathParam("id") int id) throws SQLException {
//        Projet projet = getDerby().findById("id", id);
//
//        if (projet == null) {
//            return Response.status(404).entity(projet).build();
//        } else {
//            return Response.status(200).entity(projet).build();
//        }
//    }
//
//    /**
//     * 
//     * @param champs
//     * @param valeur
//     * @return
//     * @throws SQLException 
//     */
//    @GET
//    @Path("/parametre/{champs}/{valeur}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getListByParam(@PathParam("champs") String champs, @PathParam("valeur") String valeur) throws SQLException {
//        List<Projet> projets = (List<Projet>) getDerby().findByProperty(champs, valeur);
//
//        if (projets == null) {
//            return Response.status(404).entity(projets).build();
//        } else {
//            return Response.status(200).entity(projets).build();
//        }
//    }
//
//    /**
//     * 
//     * @param id
//     * @return
//     * @throws SQLException 
//     */
//    @DELETE
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getDeleteListByParam(@PathParam("id") int id) throws SQLException {
//        Projet projet = getDerby().findById("id", id);
//        getDerby().delete(projet);
//        Projet projetDelete = getDerby().findById("id", id);
//
//        if (projetDelete == null) {
//            return Response.status(200).entity(projet).build();
//        } else {
//            return Response.status(404).entity(projet).build();
//        }
//    }
//
//    /**
//     * 
//     * @param projet
//     * @throws SQLException 
//     */
//    @POST
//    @Path("/")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void create(Projet projet) throws SQLException {
//        getDerby().create(projet);
//    }
//
//    /**
//     * 
//     * @param projet
//     * @throws SQLException 
//     */
//    @PUT
//    @Path("/")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void update(Projet projet) throws SQLException {
//        getDerby().create(projet);
//    }
//
//    /**
//     * @return the derby
//     */
//    public ProjetRepository getDerby() {
//        return derby;
//    }
//
//    /**
//     * @param derby the derby to set
//     */
//    public void setDerby(ProjetRepository derby) {
//        this.derby = derby;
//    }
//
//    /**
//     * @return the mysql
//     */
//    public ProjetRepository getMysql() {
//        return mysql;
//    }
//
//    /**
//     * @param mysql the mysql to set
//     */
//    public void setMysql(ProjetRepository mysql) {
//        this.mysql = mysql;
//    }
//
//}
