package com.rns.testes.java.controller.event;

import org.springframework.context.ApplicationEvent;
import javax.servlet.http.HttpServletResponse;

public class HeaderLocationEvent extends ApplicationEvent {

    private final HttpServletResponse response;
    private final Object id;

    public HeaderLocationEvent(Object source, HttpServletResponse response, Object id) {
        super(source);
        this.response = response;
        this.id = id;
    }

    public Object getId() {
        return id;
    }

    public HttpServletResponse getResponse() {
        return response;
    }
}
