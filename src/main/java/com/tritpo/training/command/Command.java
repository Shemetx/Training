package com.tritpo.training.command;

import com.tritpo.training.command.impl.ResponseContextImpl;

public interface Command {
    /**
     * executes user request
     *
     * @param requestContext an {@link RequestContext} object that contains client request
     * @return A response in the form of {@link ResponseContextImpl} object.
     */
    ResponseContext execute(RequestContext requestContext);
}
