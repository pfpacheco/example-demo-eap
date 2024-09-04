package com.example.demo.eap.controller;

import com.example.demo.eap.controller.v1.request.demo.DemoRequest;
import com.example.demo.eap.controller.v1.request.demo.DemoRequestBody;
import com.example.demo.eap.persistence.entities.Demo;
import com.example.demo.eap.services.DemoService;
import com.example.demo.eap.controller.v1.response.demo.DemoResponse;
import com.example.demo.eap.controller.v1.response.demo.DemoResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import jakarta.ws.rs.core.Response;
import jakarta.xml.bind.JAXB;

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

    @SuppressWarnings("unused")
    public Response add(DemoRequestBody request) {

        Gson gson = new GsonBuilder().setDateFormat(DATE_TIME_PATTERN).create();

        try {
            

            DemoRequest bodyRequest = request.getBody();

            Demo entity = new Demo();

            entity.setName(bodyRequest.getName());
            entity.setDescription(bodyRequest.getDescription());

            service.add(entity);
            Demo result = service.findByName(entity.getName());
            
            DemoResponse response = 
                    new DemoResponse(201, Response.Status.CREATED.toString(), 
                            gson.toJsonTree(String.format("Entity id: %s persisted!", 
                                    result.getId())).getAsJsonObject());
            
            DemoResponseBody body = DemoResponseBody
                    .builder().body(response).build();

            return Response
                    .status(Response.Status.CREATED)
                    .entity(gson.toJson(body)).build();
        } catch (JsonSyntaxException e) {
            DemoResponse response = 
                new DemoResponse(500, Response.Status.INTERNAL_SERVER_ERROR.toString(), 
                        gson.toJsonTree("Entity could not be persisted!")
                                .getAsJsonObject());
            
            DemoResponseBody body = DemoResponseBody
                .builder().body(response).build();
            
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(gson.toJson(body)).build();
        }
    }
    
    public Response findById(Long id) {
        
        Gson gson = new GsonBuilder().setDateFormat(DATE_TIME_PATTERN).create();
        
        try {
            Optional<Demo> entity = Optional.ofNullable(service.findById(id));
            
            if (!entity.isPresent()) {
                throw new NoSuchElementException(String.format("No entity found for id: %s", id));
            }
           
            DemoResponse response = 
                    new DemoResponse(200, Response.Status.OK.toString(), 
                            gson.toJsonTree(entity.get()).getAsJsonObject());
            
            DemoResponseBody body = DemoResponseBody
                    .builder().body(response).build();
            
            return Response
                    .status(Response.Status.OK)
                    .entity(gson.toJson(body)).build();
        
        } catch (JsonSyntaxException e) {
            DemoResponse response = 
                new DemoResponse(500, Response.Status.INTERNAL_SERVER_ERROR.toString(), 
                    gson.toJsonTree("Entity could not be found!")
                            .getAsJsonObject());
            
            DemoResponseBody body = DemoResponseBody
                .builder().body(response).build();
            
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(gson.toJson(body)).build();
        }
    }
}
