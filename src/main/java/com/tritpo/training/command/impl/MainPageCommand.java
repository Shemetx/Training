package com.tritpo.training.command.impl;

import com.tritpo.training.command.Command;
import com.tritpo.training.command.RequestContext;
import com.tritpo.training.command.ResponseContext;


public class MainPageCommand implements Command {

    @Override
    public ResponseContext execute(RequestContext requestContext) {

        return new ResponseContextImpl(ResponseContextImpl.ResponseType.FORWARD, "WEB-INF/jsp/mainPage.jsp");
    }
}
