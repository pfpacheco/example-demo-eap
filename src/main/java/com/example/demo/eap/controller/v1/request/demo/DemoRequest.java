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
public class DemoRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -8812394871298374012L;
    
    @NotNull private String name;
    private String description;

    public DemoRequest() {
    }

    public DemoRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
