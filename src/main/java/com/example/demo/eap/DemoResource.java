package com.example.demo.eap;

import com.example.demo.eap.controller.DemoController;
import com.example.demo.eap.controller.v1.request.demo.DemoRequestBody;

import com.google.gson.Gson;

import jakarta.inject.Inject;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


/**
 *
 * @author ppacheco
 */
@Path("/api")
public class DemoResource {
    
    @Inject
    DemoController controller;
    
    @POST
    @Path("/rest/v1/demo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(String request) {
        Gson gson = new Gson();
        DemoRequestBody req = gson.fromJson(request, DemoRequestBody.class);
        return controller.add(req);
    }
    
    @GET
    @Path("/rest/v1/demo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return controller.findAll();
    }

    @GET
    @Path("/rest/v1/demo/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByName(@PathParam("name") String name) {
        return controller.findByName(name);
    }
    
    
    @GET
    @Path("/rest/v1/demo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        return controller.findById(id);
    }
}
