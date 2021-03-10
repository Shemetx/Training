package com.tritpo.training.controller;

import com.tritpo.training.command.Command;
import com.tritpo.training.command.CommandFactory;
import com.tritpo.training.command.ResponseContext;
import com.tritpo.training.command.impl.CustomRequestContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/home","/admin","/user","/teacher"})
public class FrontServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    /**
     * Processes the request by obtaining a command from the {@link javax.servlet.http.HttpServletRequest} object,
     * execute this command and redirects or forwards on destination page depending on the result of the command execution.
     *
     * @param req an {@link javax.servlet.http.HttpServletRequest} object that contains client request
     * @param resp an {@link javax.servlet.http.HttpServletResponse} object that contains the response
     *                 the servlet sends to the client
     */
    public void processRequest(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
        String commandParamName = req.getParameter("command");

        Command command = CommandFactory.command(commandParamName);
        final ResponseContext execute = command.execute(new CustomRequestContext(req));

        if (execute.getResponseType() == ResponseContext.ResponseType.FORWARD) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(execute.getPage());
            requestDispatcher.forward(req,resp);
        } else {
            resp.sendRedirect(req.getServletContext().getContextPath() + execute.getPage());
        }
    }
}
