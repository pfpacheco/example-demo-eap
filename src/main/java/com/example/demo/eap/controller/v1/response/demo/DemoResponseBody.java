package com.example.demo.eap.controller.v1.response.demo;

import io.smallrye.common.constraint.NotNull;

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
public class DemoResponseBody implements Serializable {
        
    private static final long serialVersionUID = -4982765873920483751L;

    @NotNull private DemoResponse body;

    public DemoResponseBody() {
    }

    public DemoResponseBody(DemoResponse body) {
        this.body = body;
    }
}
