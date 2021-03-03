package com.tritpo.training.command;


public interface ResponseContext {
    enum ResponseType {
        FORWARD,
        REDIRECT
    }

    String getPage();

    ResponseType getResponseType();
}
