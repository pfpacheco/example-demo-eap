package com.example.demo.eap.controller.v1.response.demo;

import com.google.gson.JsonObject;

import io.smallrye.common.constraint.NotNull;

import java.io.Serial;
import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ppacheco
 */
@Builder
@Getter
@Setter
public class DemoResponse implements Serializable {
    
    @Serial
    private static final long serialVersionUID = -5812394871298374012L;
    
    @NotNull private int code;
    @NotNull private String status;
    @NotNull private JsonObject message;

    public DemoResponse() {
    }

    public DemoResponse(int code, String status, JsonObject message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }
}
