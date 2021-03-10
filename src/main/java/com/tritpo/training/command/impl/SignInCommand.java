package com.tritpo.training.command.impl;

import com.tritpo.training.command.Command;
import com.tritpo.training.command.RequestContext;
import com.tritpo.training.command.ResponseContext;
import com.tritpo.training.domain.User;
import com.tritpo.training.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.Optional;


public class SignInCommand implements Command {

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        String login = requestContext.getParameter("login");
        String password = requestContext.getParameter("password");

        User user = User.Builder.newInstance()
                .setLogin(login)
                .setPassword(password)
                .build();

        Optional<User> isExists = UserService.getInstance().signIn(user);

        if (isExists.isPresent()) {
            HttpSession session = requestContext.getSession();
            session.setAttribute("role", isExists.get().getRole().getName());
            session.setAttribute("userId", isExists.get().getId());
            session.setAttribute("isBanned", isExists.get().isBanned());

            return new ResponseContextImpl(ResponseContextImpl.ResponseType.REDIRECT,
                    "/home?command=mainPage");
        }

        return new ResponseContextImpl(ResponseContextImpl.ResponseType.REDIRECT,
                "/home?command=toSignIn");
    }
}
