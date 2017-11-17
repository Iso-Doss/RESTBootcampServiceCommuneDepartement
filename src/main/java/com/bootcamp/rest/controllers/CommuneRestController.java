
package com.bootcamp.servicecrud.rest.controller;

import com.bootcamp.jpa.repositories.BaseRepository;
import com.bootcamp.servicecrud.jpa.entities.Commune;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


@Path("/commune")
@Api(value = "communes", description = "web service on all the communes available")
public class CommuneRestController {

    BaseRepository<Commune> communeRepository = new BaseRepository("tpRest", Commune.class);

    //Annotation JAX-RS2
    @Context
    UriInfo uriInfo;

    /**
     *URI d'acces a toutes les communes
     * @return
     * @throws java.sql.SQLException
     */
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get all the communes")
    public List<Commune> getCommunes() throws SQLException {
        //obtenir la liste des communes de la base de donnees
        List<Commune> communes = communeRepository.findAll();
        return communes;
    }

    /**
     *URI d'acces a une commune
     * @param id
     * @return
     * @throws java.sql.SQLException
     */
    @GET
    @Path("/commune/{id}")
    @ApiOperation(value = "Get a city knowing its id")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@ApiParam(value = "Id of the city", required = true) @PathParam("id") int id) throws SQLException {
        Commune commune = communeRepository.findByPropertyUnique("id", id);

        if (commune != null) {
            commune.setSelf(
                    Link.fromUri(uriInfo.getAbsolutePath())
                    .rel("self")
                    .type("GET")
                    .build());
            return Response.accepted(commune).links(commune.getSelf()).build();
        } else {
            return Response.status(404).entity(commune).build();
        }
    }
    
    /**
     * methode de recherche sur un le nom du departement
     * @param nom
     * @return
     * @throws SQLException
     */
    @GET
    @Path("/departement/{nom}")
    @ApiOperation(value = "Get a city knowing its name")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByNom(@ApiParam(value = "Name of the city", required = true) @PathParam("nom") String nom) throws SQLException {
        Commune commune = communeRepository.findByPropertyUnique("nom", nom);
        
        if (commune != null) {
            commune.setSelf(
                    Link.fromUri(uriInfo.getAbsolutePath())
                    .rel("self")
                    .type("GET")
                    .build());
            return Response.accepted(commune).links(commune.getSelf()).build();
        } else {
            return Response.status(404).entity(commune).build();
        }
    }

    /**
     * methode de creation de la commune
     * @param commune
     * @return
     */
    @POST
    @Path("/create")
    @ApiOperation(value = "Create a new city")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create( @ApiParam(value = "The city entity", required = true) Commune commune) {
        String output = " Felicitations objet cree avec succes : ";
        try {
            communeRepository.create(commune);
            return Response.status(200).entity(output + commune.getNom()).build();
        } catch (SQLException ex) {
            return Response.status(404).entity("Erreur: Objet non cree").build();
        }
    }
    
    /**
     * methode de modification de la commune
     * @param commune
     * @return
     */
    @PUT
    @Path("/update")
    @ApiOperation(value = "Update a city")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@ApiParam(value = "The city entity", required = true) Commune commune) {
        String output = " Felicitations. Modification effectuee avec succes pour : ";
        try {
            communeRepository.update(commune);
            return Response.status(200).entity(output + commune.getNom()).build();
        } catch (SQLException ex) {
            return Response.status(404).entity("Erreur: Objet non mis a jour").build();
        }

    }
//
    /**
     * methode permettant de verifier si un attribut fait partie d'une liste de champ indiques
     * @param fields
     * @param field
     * @return
     */
    private boolean check(String[] fields, String field) {

        for (String field1 : fields) {
            if (field.equals(field1)) {
                return true;
            }
        }
        return false;
    }
}
