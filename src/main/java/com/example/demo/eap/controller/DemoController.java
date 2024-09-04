package com.example.demo.eap.controller;

import com.example.demo.eap.controller.v1.request.demo.DemoRequest;
import com.example.demo.eap.controller.v1.request.demo.DemoRequestBody;
import com.example.demo.eap.persistence.entities.Demo;
import com.example.demo.eap.services.DemoService;
import com.example.demo.eap.controller.v1.response.demo.DemoResponse;
import com.example.demo.eap.controller.v1.response.demo.DemoResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author ppacheco
 */
@ApplicationScoped
public class DemoController {

    @Inject
    DemoService service;

    @SuppressWarnings("unused")
    public Response add(DemoRequestBody request) {

        Gson gson = new Gson();

        try {

            DemoRequest bodyRequest = request.getBody();

            Demo entity = new Demo();

            entity.setName(bodyRequest.getName());
            entity.setDescription(bodyRequest.getDescription());

            service.add(entity);
            Demo result = service.findByName(entity.getName());
            
            DemoResponse response = 
                    new DemoResponse(201, Response.Status.CREATED.toString(), 
                            String.format("Entity id: %s persisted!", 
                                    result.getId()));
            
            DemoResponseBody body = DemoResponseBody
                    .builder().body(response).build();

            return Response
                    .status(Response.Status.CREATED)
                    .entity(gson.toJson(body)).build();
        } catch (JsonSyntaxException e) {
            DemoResponse response = 
                    new DemoResponse(500, Response.Status.INTERNAL_SERVER_ERROR.toString(), 
                            "Entity could not be persisted");
            
            DemoResponseBody body = DemoResponseBody
                .builder().body(response).build();
            
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(gson.toJson(body)).build();
        }
    }
}
