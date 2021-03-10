package com.tritpo.training.command.impl;

import com.tritpo.training.command.Command;
import com.tritpo.training.command.RequestContext;
import com.tritpo.training.command.ResponseContext;


public class ToSignInCommand implements Command {

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        return new ResponseContextImpl(ResponseContext.ResponseType.FORWARD, "/WEB-INF/jsp/signIn.jsp");

    }
}
