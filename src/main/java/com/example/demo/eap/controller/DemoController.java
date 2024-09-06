package com.example.demo.eap.controller;

import com.example.demo.eap.controller.v1.request.demo.DemoRequest;
import com.example.demo.eap.controller.v1.request.demo.DemoRequestBody;
import com.example.demo.eap.persistence.entities.Demo;
import com.example.demo.eap.services.DemoService;
import com.example.demo.eap.controller.v1.response.demo.DemoResponse;
import com.example.demo.eap.controller.v1.response.demo.DemoResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import jakarta.ws.rs.core.Response;
import java.util.List;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 *
 * @author ppacheco
 */
@ApplicationScoped
public class DemoController {

    @Inject
    DemoService service;

    private static final String DATE_TIME_PATTERN = "dd-MM-yyyy HH:mm:ss";
    
    private final Gson gson;

    public DemoController() {
        this.gson = new GsonBuilder().setDateFormat(DATE_TIME_PATTERN).create();
    }
    
    

    /**
     * 
     * @param request
     * @return jakarta.ws.rs.core.Response
     */
    public Response add(DemoRequestBody request) {

        try {

            DemoRequest bodyRequest = request.getBody();

            Demo entity = new Demo();

            entity.setName(bodyRequest.getName());
            entity.setDescription(bodyRequest.getDescription());

            service.add(entity);
            Demo result = service.findByName(entity.getName());

            DemoResponse response
                    = new DemoResponse(201, Response.Status.CREATED.toString(),
                            this.gson.toJsonTree(String.format("Entity id: %s persisted!",
                                    result.getId())).getAsJsonObject());

            DemoResponseBody body = DemoResponseBody
                    .builder().body(response).build();

            return Response
                    .status(Response.Status.CREATED)
                    .entity(this.gson.toJson(body)).build();
        } catch (JsonSyntaxException e) {
            
            DemoResponse response
                    = new DemoResponse(500, Response.Status.INTERNAL_SERVER_ERROR.toString(),
                            this.gson.toJsonTree("Internal server error!")
                                    .getAsJsonObject());
            
            DemoResponseBody body = DemoResponseBody
                    .builder().body(response).build();

            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(this.gson.toJson(body)).build();
        }
    }

    /**
     * 
     * @param id
     * @return jakarta.ws.rs.core.Response
     */
    public Response findById(Long id) {

        try {
            Optional<Demo> entity = Optional.ofNullable(service.findById(id));

            if (entity.isEmpty()) {
                throw new NoSuchElementException(String.format("No entity found for id: %s", id));
            }

            DemoResponse response
                    = new DemoResponse(200, Response.Status.OK.toString(),
                            this.gson.toJsonTree(entity.get()).getAsJsonObject());

            DemoResponseBody body = DemoResponseBody
                    .builder().body(response).build();

            return Response
                    .status(Response.Status.OK)
                    .entity(this.gson.toJson(body)).build();

        } catch (JsonSyntaxException e) {
            
            DemoResponse response
                    = new DemoResponse(500, Response.Status.INTERNAL_SERVER_ERROR.toString(),
                            this.gson.toJsonTree("Internal server error!")
                                    .getAsJsonObject());

            DemoResponseBody body = DemoResponseBody
                    .builder().body(response).build();

            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(this.gson.toJson(body)).build();
        }   
    }
    
    public Response findByName(String name) {
        
        try {
            Optional<Demo> entity = Optional.ofNullable(service.findByName(name));
            
            if ( entity.isEmpty() ) {
                throw new NoSuchElementException(String.format("No entity found for name: %s", name));
            }
            
            DemoResponse response = 
                    new DemoResponse(200, Response.Status.OK.toString(), 
                            this.gson.toJsonTree(entity.get()).getAsJsonObject());
            
            DemoResponseBody body = DemoResponseBody
                    .builder().body(response).build();
            
            return Response.status(Response.Status.OK)
                    .entity(this.gson.toJson(body)).build();
            
        } catch (JsonSyntaxException e) {
            
            DemoResponse response 
                    = new DemoResponse(500, Response.Status.INTERNAL_SERVER_ERROR.toString(), 
                            this.gson.toJsonTree("Internal server error!")
                                    .getAsJsonObject());

            DemoResponseBody body = DemoResponseBody
                    .builder().body(response).build();

            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(this.gson.toJson(body)).build();
        }
    }
    
    /**
     * 
     * @return jakarta.ws.rs.core.Response
     */
    public Response findAll() {
        
        try {
            
            Optional<List<Demo>> list = Optional.ofNullable(service.findAll());
            
            if (list.isEmpty()) {
                throw new NoSuchElementException("Could not find a list of entities!");
            }
                            
            JsonArray demos = new JsonArray();
            
            list.get().forEach(entity -> {
                demos.add(this.gson.toJsonTree(entity));
            });
            
            JsonObject entityJSON = new JsonObject();
            entityJSON.add("demos", demos);
            
            DemoResponse response 
                    = new DemoResponse(200, Response.Status.OK.toString(), entityJSON);
            
            DemoResponseBody body = DemoResponseBody
                    .builder().body(response).build();
            
            return Response
                    .status(Response.Status.OK)
                    .entity(this.gson.toJson(body)).build();
            
        } catch (JsonSyntaxException e) {
            
            DemoResponse response
                    = new DemoResponse(500, Response.Status.INTERNAL_SERVER_ERROR.toString(),
                            this.gson.toJsonTree("Internal server error!")
                                    .getAsJsonObject());

            DemoResponseBody body = DemoResponseBody
                    .builder().body(response).build();
            
            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(gson.toJson(body)).build();
        }
    }
}
