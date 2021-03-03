package com.tritpo.training.command.impl;

import com.tritpo.training.command.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class CustomRequestContext implements RequestContext {
    private final HttpServletRequest httpServletRequest;

    public CustomRequestContext(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public void setAttribute(String name, Object attr) {
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute(name, attr);
    }

    @Override
    public String getParameter(String param) {
        return httpServletRequest.getParameter(param);
    }

    public Object getSessionAttribute(String param) {
        HttpSession session = httpServletRequest.getSession();
        return session.getAttribute(param);
    }

    @Override
    public HttpSession getSession() {
        return httpServletRequest.getSession();
    }

    public void setRequestAttribute(String name, Object attr) {
        httpServletRequest.setAttribute(name, attr);
    }

}
