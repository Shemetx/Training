package com.tritpo.training.command.impl;

import com.tritpo.training.command.Command;
import com.tritpo.training.command.RequestContext;
import com.tritpo.training.command.ResponseContext;
import com.tritpo.training.domain.Role;
import com.tritpo.training.domain.User;
import com.tritpo.training.service.UserService;

import java.util.Optional;


public class SignUpCommand implements Command {

    private UserService userService = UserService.getInstance();

    @Override
    public ResponseContext execute(RequestContext requestContext) {

        String login = requestContext.getParameter("login");
        String password = requestContext.getParameter("password");
        String email = requestContext.getParameter("email");
        String firstName = requestContext.getParameter("firstName");
        String secondName = requestContext.getParameter("secondName");

        User user = User.Builder.newInstance()
                .setLogin(login)
                .setPassword(password)
                .setEmail(email)
                .setFirstName(firstName)
                .setSecondName(secondName)
                .setRole(Role.USER)
                .setIsBanned(false)
                .build();


        Optional<User> finalUser = userService.signUp(user);

        if (finalUser.isPresent()) {
            return new ResponseContextImpl(ResponseContextImpl.ResponseType.REDIRECT,
                    "/home?command=toSignIn");
        } else {
            return new ResponseContextImpl(ResponseContextImpl.ResponseType.REDIRECT,
                    "/home?command=toSignUp");
        }

    }


}
