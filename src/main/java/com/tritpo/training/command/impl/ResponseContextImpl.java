package com.tritpo.training.command.impl;


import com.tritpo.training.command.ResponseContext;

public class ResponseContextImpl implements ResponseContext {
    /**
     * Forward or Redirect
     */
    private ResponseContext.ResponseType responseType;
    /**
     * page address
     */
    private String page;

    public ResponseContextImpl(ResponseType responseType, String page) {
        this.responseType = responseType;
        this.page = page;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
