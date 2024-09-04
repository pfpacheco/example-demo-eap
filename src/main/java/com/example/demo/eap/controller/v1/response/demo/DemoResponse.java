package com.example.demo.eap.controller.v1.response.demo;

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
    @NotNull private String message;

    public DemoResponse() {
    }

    public DemoResponse(int code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }
}
