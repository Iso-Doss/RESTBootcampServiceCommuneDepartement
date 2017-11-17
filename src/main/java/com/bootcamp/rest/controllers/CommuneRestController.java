package com.bootcamp.rest.controllers;

import com.bootcamp.AppConstants;
import io.swagger.annotations.*;
import java.sql.SQLException;
import java.util.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.bootcamp.entities.*;
import com.bootcamp.jpa.*;
import java.beans.*;
import java.lang.reflect.*;
import javax.ws.rs.QueryParam;

@Path("/communes")
@Api(value = "communes", description = "web service on all the communes available")
public class CommuneRestController {

    private CommuneRepository communeRepository = new CommuneRepository(AppConstants.PERSISTENCE_UNIT);

    //Annotation JAX-RS2
    @Context
    private UriInfo uriInfo;

    /**
     * URI d'acces a toutes les communes
     *
     * @return
     * @throws java.sql.SQLException
     */
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get all the communes")
    public Response getList() throws SQLException {
        List<Commune> communes = getCommuneRepository().findAll();

        if (communes == null) {
            return Response.status(404).entity(communes).build();
        } else {
            return Response.status(200).entity(communes).build();
        }
    }

    /**
     * URI d'acces a une commune grace a son id
     *
     * @param id
     * @return
     * @throws java.sql.SQLException
     */
    @GET
    @Path("/{id}")
    @ApiOperation(value = "Get a city knowing its id")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@ApiParam(value = "Id of the city", required = true) @PathParam("id") int id) throws SQLException {
        Commune commune = getCommuneRepository().findByPropertyUnique("id", id);

        if (commune != null) {
            commune.setSelf(Link.fromUri(getUriInfo().getAbsolutePath())
                    .rel("self")
                    .type("GET")
                    .build());
            return Response.accepted(commune).links(commune.getSelf()).build();
        } else {
            return Response.status(404).entity(commune).build();
        }
    }

    /**
     * methode de recherche sur champ du departement
     *
     * @param champs
     * @param valeur
     * @return
     * @throws SQLException
     */
    @GET
    @Path("/parametre/{champs}/{valeur}")
    @ApiOperation(value = "Get a city knowing its name")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByNom(@ApiParam(value = "Champs de recherche", required = true) @PathParam("champs") String champs, @ApiParam(value = "valeur du champs", required = true) @PathParam("valeur") String valeur) throws SQLException {
        Commune commune = getCommuneRepository().findByPropertyUnique(champs, valeur);
        if (commune != null) {
            commune.setSelf(Link.fromUri(getUriInfo().getAbsolutePath())
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
     *
     * @param commune
     * @return
     */
    @POST
    @Path("/create")
    @ApiOperation(value = "Create a new city")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@ApiParam(value = "The city entity", required = true) Commune commune) {
        String output = " Felicitations objet cree avec succes : ";
        try {
            getCommuneRepository().create(commune);
            return Response.status(200).entity(output + commune.getNom()).build();
        } catch (SQLException ex) {
            return Response.status(404).entity("Erreur: Objet non cree").build();
        }
    }

    /**
     * methode de modification de la commune
     *
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
            getCommuneRepository().update(commune);
            return Response.status(200).entity(output + commune.getNom()).build();
        } catch (SQLException ex) {
            return Response.status(404).entity("Erreur: Objet non mis a jour").build();
        }

    }

    @GET
    @Path("/reponse_partiel/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByIdParam(@PathParam("id") int id, @QueryParam("fields") String fields) throws SQLException, IllegalArgumentException, IllegalAccessException, IntrospectionException, InvocationTargetException {
        String[] fieldArray = fields.split(",");
        //CommuneRepository communeRepository = new CommuneRepository("punit-mysql");
        Commune commune = getCommuneRepository().findById(id);
        Map<String, Object> responseMap = new HashMap<>();
        PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(Commune.class).getPropertyDescriptors();

        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {

            Method method = propertyDescriptor.getReadMethod();
            if (check(fieldArray, propertyDescriptor.getName())) {
                responseMap.put(propertyDescriptor.getName(), method.invoke(commune));
            }
            System.out.println(method.invoke(commune));
        }
        return Response.status(200).entity(responseMap).build();
    }

    /**
     * methode permettant de verifier si un attribut fait partie d'une liste de
     * champ indiques
     *
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

    /**
     * @return the communeRepository
     */
    public CommuneRepository getCommuneRepository() {
        return communeRepository;
    }

    /**
     * @param communeRepository the communeRepository to set
     */
    public void setCommuneRepository(CommuneRepository communeRepository) {
        this.communeRepository = communeRepository;
    }

    /**
     * @return the uriInfo
     */
    public UriInfo getUriInfo() {
        return uriInfo;
    }

    /**
     * @param uriInfo the uriInfo to set
     */
    public void setUriInfo(UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }
}
