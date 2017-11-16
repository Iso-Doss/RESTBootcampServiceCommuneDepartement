/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.rest.controllers;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 *
 * @author Iso-Doss
 */
@Path("/hello")
public class RestWelcomeController {

    @GET
    @Path("/{msg}")
    public Response getMsg(@PathParam("msg") String msg) {
        String output = " get Jersey say : " + msg;
        return Response.status(200).entity(output).build();
    }

    //QueryParam
    @GET
    @Path("/test")
    public Response getQMsg(@QueryParam("param") String msg, @QueryParam("param2") String msg2) {
        String output = " QueryParam Jersey say : " + msg + " et " + msg2;
        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("/{param}/{param2}")
    public Response get3Msg(@PathParam("param") String msg, @PathParam("param2") String msg2) {
        String output = " get Jersey say : " + msg + " et " + msg2;
        return Response.status(200).entity(output).build();
    }
}
