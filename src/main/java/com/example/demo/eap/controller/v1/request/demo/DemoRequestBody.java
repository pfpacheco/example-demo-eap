package com.example.demo.eap.controller.v1.request.demo;

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
public class DemoRequestBody implements Serializable {
    
    @Serial
    private static final long serialVersionUID = -7812394871298374012L;

    @NotNull private DemoRequest body;

    public DemoRequestBody() {
    }

    public DemoRequestBody(DemoRequest body) {
        this.body = body;
    }
}
