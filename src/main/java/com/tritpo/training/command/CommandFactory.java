package com.tritpo.training.command;

import com.epam.jwd.command.impl.*;
import com.tritpo.training.command.impl.*;


public class CommandFactory {
    /**
     * @param command - contains name of the command from {@link javax.servlet.http.HttpServletRequest}
     * @return created instance of {@link Command}
     */
    public static Command command(String command) {
        if (command == null) {
            return new MainPageCommand();
        }

        switch (CommandType.valueOfName(command)) {
            case SIGN_UP:
                return new SignUpCommand();
            case SIGN_IN:
                return new SignInCommand();
            case TO_SIGN_UP:
                return new ToSignUpCommand();
            case MAIN_PAGE:
                return  new MainPageCommand();
            case TO_SIGN_IN:
                return new ToSignInCommand();
            default:
                return new MainPageCommand();
        }
    }
}
