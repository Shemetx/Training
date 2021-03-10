package com.tritpo.training.command;

import javax.servlet.http.HttpSession;

public interface RequestContext {
    void setAttribute(String name, Object attr);

    String getParameter(String param);

    Object getSessionAttribute(String param);

    HttpSession getSession();

    void setRequestAttribute(String name, Object attr);
}
