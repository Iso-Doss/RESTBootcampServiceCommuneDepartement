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

@Path("/departements")
@Api(value = "/departements", description = "web service on all the departements available")
@Produces(MediaType.APPLICATION_JSON)
public class DepartementRestController {

    private DepartementRepository departementRepository = new DepartementRepository(AppConstants.PERSISTENCE_UNIT);

    //Annotation JAX-RS2
    @Context
    private UriInfo uriInfo;

    /**
     * URI d'acces a toutes les departements
     *
     * @return
     * @throws java.sql.SQLException
     */
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get all the departements")
    public Response getList() throws SQLException {
        List<Departement> departements = getDepartementRepository().findAll();

        if (departements == null) {
            return Response.status(404).entity(departements).build();
        } else {
            return Response.status(200).entity(departements).build();
        }
    }

    /**
     * URI d'acces a une departement grace a son id
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
        Departement departement = getDepartementRepository().findByPropertyUnique("id", id);

        if (departement != null) {
            departement.setSelf(Link.fromUri(getUriInfo().getAbsolutePath())
                    .rel("self")
                    .type("GET")
                    .build());
            return Response.accepted(departement).links(departement.getSelf()).build();
        } else {
            return Response.status(404).entity(departement).build();
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
        List<Departement> departements = getDepartementRepository().findByProperty(champs, valeur);
        if (departements != null) {
            for (int i = 0; i < departements.size(); i++) {
                departements.get(i).setSelf(Link.fromUri(getUriInfo().getAbsolutePath())
                        .rel("self")
                        .type("GET")
                        .build());
                return Response.accepted(departements.get(i)).links(departements.get(i).getSelf()).build();
            }
            return Response.status(200).entity(departements).build();
        } else {
            return Response.status(404).entity(departements).build();
        }
    }

    /**
     * methode de creation de la departement
     *
     * @param departement
     * @return
     */
    @POST
    @Path("/create")
    @ApiOperation(value = "Create a new city")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@ApiParam(value = "The city entity", required = true) Departement departement) {
        String output = " Felicitations objet cree avec succes : ";
        try {
            getDepartementRepository().create(departement);
            return Response.status(200).entity(output + departement.getNom()).build();
        } catch (SQLException ex) {
            return Response.status(404).entity("Erreur: Objet non cree").build();
        }
    }

    /**
     * methode de modification de la departement
     *
     * @param departement
     * @return
     */
    @PUT
    @Path("/update")
    @ApiOperation(value = "Update a city")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@ApiParam(value = "The city entity", required = true) Departement departement) {
        String output = " Felicitations. Modification effectuee avec succes pour : ";
        try {
            getDepartementRepository().update(departement);
            return Response.status(200).entity(output + departement.getNom()).build();
        } catch (SQLException ex) {
            return Response.status(404).entity("Erreur: Objet non mis a jour").build();
        }

    }

    @GET
    @Path("/reponse_partiel/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByIdParam(@PathParam("id") int id, @QueryParam("fields") String fields) throws SQLException, IllegalArgumentException, IllegalAccessException, IntrospectionException, InvocationTargetException {
        String[] fieldArray = fields.split(",");
        //DepartementRepository departementRepository = new DepartementRepository("punit-mysql");
        Departement departement = getDepartementRepository().findById(id);
        Map<String, Object> responseMap = new HashMap<>();
        PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(Departement.class).getPropertyDescriptors();

        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {

            Method method = propertyDescriptor.getReadMethod();
            if (check(fieldArray, propertyDescriptor.getName())) {
                responseMap.put(propertyDescriptor.getName(), method.invoke(departement));
            }
            System.out.println(method.invoke(departement));
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
     * @return the departementRepository
     */
    public DepartementRepository getDepartementRepository() {
        return departementRepository;
    }

    /**
     * @param departementRepository the departementRepository to set
     */
    public void setDepartementRepository(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
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
