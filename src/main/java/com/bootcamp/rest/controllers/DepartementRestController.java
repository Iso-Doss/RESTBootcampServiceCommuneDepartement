
package com.bootcamp.servicecrud.rest.controller;

import com.bootcamp.jpa.repositories.BaseRepository;
import com.bootcamp.servicecrud.jpa.entities.Departement;
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


@Path("/departement")
@Api(value = "/departement", description = "web service on all the departements available")
@Produces(MediaType.APPLICATION_JSON)
public class DepartementRestController {

     /**
      * instanciation d'un repository departement
      * permettant d'acceder a toutes les methodes d'appel aux donnees d'un departement
      */
    //DepartementRepository departementRepository = new DepartementRepository("tpRest");
    BaseRepository<Departement> departementRepository = new BaseRepository("tpRest", Departement.class);

    //Annotation JAX-RS2
    @Context
    UriInfo uriInfo;

    /**
     *URI d'acces a tous les departements
     * @return
     * @throws java.sql.SQLException
     */
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get all the departements")
    public List<Departement> getDepartements() throws SQLException {
        //obtenir la liste des departements de la base de donnees
        List<Departement> departements = departementRepository.findAll();
        return departements;
    }

    /**
     *URI d'acces a un departement
     * @param id
     * @return
     * @throws java.sql.SQLException
     */
    @GET
    @Path("/departement/{id}")
    @ApiOperation(value = "Get a departement knowing its id")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@ApiParam(value = "Id of the departement", required = true) @PathParam("id") int id) throws SQLException {

        Departement departement = departementRepository.findByPropertyUnique("id", id);

        if (departement != null) {
            departement.setSelf(
                    Link.fromUri(uriInfo.getAbsolutePath())
                    .rel("self")
                    .type("GET")
                    .build());
            return Response.accepted(departement).links(departement.getSelf()).build();
        } else {
            return Response.status(404).entity(departement).build();
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
    @ApiOperation(value = "Get a departement knowing its name")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByNom(@ApiParam(value = "Name of the departement", required = true) @PathParam("nom") String nom) throws SQLException {
        Departement departement = departementRepository.findByPropertyUnique("nom", nom);
        
        if (departement != null) {
            departement.setSelf(
                    Link.fromUri(uriInfo.getAbsolutePath())
                    .rel("self")
                    .type("GET")
                    .build());
            return Response.accepted(departement).links(departement.getSelf()).build();
        } else {
            return Response.status(404).entity(departement).build();
        }
    }
    
    /**
     * methode de recherche sur un le numero du departement
     * @param numero
     * @return
     * @throws SQLException
     */
    @GET
    @Path("/departement/{numero}")
    @ApiOperation(value = "Get a departement knowing its postal code")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByNumero(@ApiParam(value = "Postal code of the departement", required = true) @PathParam("numero") int numero) throws SQLException {
        Departement departement = departementRepository.findByPropertyUnique("numero", numero);
        
        if (departement != null) {
            departement.setSelf(
                    Link.fromUri(uriInfo.getAbsolutePath())
                    .rel("self")
                    .type("GET")
                    .build());
            return Response.accepted(departement).links(departement.getSelf()).build();
        } else {
            return Response.status(404).entity(departement).build();
        }
    }

    /**
     * methode de creation du departement
     * @param departement
     * @return
     */
    @POST
    @Path("/create")
    @ApiOperation(value = "Create a new departement")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create( @ApiParam(value = "The departement entity", required = true) Departement departement) {
        String output = " Felicitations objet cree avec succes : ";
        try {
            departementRepository.create(departement);
            return Response.status(200).entity(output + departement.getNom()).build();
        } catch (SQLException ex) {
            return Response.status(404).entity("Erreur: Objet non cree").build();
        }
    }

    /**
     * methode de modification du departement
     * @param departement
     * @return
     */
    @PUT
    @Path("/update")
    @ApiOperation(value = "Update a departement")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@ApiParam(value = "The departement entity", required = true) Departement departement) {
        String output = " Felicitations. Modification effectuee avec succes pour : ";
        try {
            departementRepository.update(departement);
            return Response.status(200).entity(output + departement.getNom()).build();
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
