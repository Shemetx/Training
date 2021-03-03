package com.tritpo.training.validator;


import com.tritpo.training.domain.User;
import com.tritpo.training.exception.ValidateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserValidator {

    private static UserValidator userValidator;

    public static UserValidator getInstance() {
        if (userValidator == null) {
            userValidator = new UserValidator();
        }

        return userValidator;
    }

    private UserValidator() {
    }

    private static final Logger logger = LogManager.getLogger(UserValidator.class);

    /**
     *  3-15 symbols
     */
    private final String LOGIN_REGEX = "^[a-zA-Z0-9._-]{3,15}$";
    /**
     *  must contain at least 8 symbols, capital letter, small letter and one digit
     */
    private final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*)(?=\\S+$).{8,}$";
    /**
     *  example@example.com
     */
    private final String EMAIL_REGEX = "^[A-z0-9._%+-]+@[A-z0-9.-]+\\.[A-z]{2,6}$";
    /**
     * should starts with capital letter 1-16 symbols
     */
    private final String FIRST_NAME_REGEX = "^(([A-Z]([A-z]){1,16}))$";
    /**
     * should starts with capital letter 1-16 symbols
     * may have second surname Example-Example
     */
    private final String SECOND_NAME_REGEX = "^(([A-z]([A-z]){1,16}([\\u0020-][A-z]([A-z]){1,16})?))$";

    public String getLOGIN_REGEX() {
        return LOGIN_REGEX;
    }

    public String getPASSWORD_REGEX() {
        return PASSWORD_REGEX;
    }

    public String getEMAIL_REGEX() {
        return EMAIL_REGEX;
    }

    public String getFIRST_NAME_REGEX() {
        return FIRST_NAME_REGEX;
    }

    public String getSECOND_NAME_REGEX() {
        return SECOND_NAME_REGEX;
    }

    public boolean validate(User user) throws ValidateException {

            validation(user.getLogin(), LOGIN_REGEX);
            validation(user.getPassword(), PASSWORD_REGEX);
            validation(user.getEmail(), EMAIL_REGEX);
            validation(user.getFirstName(), FIRST_NAME_REGEX);
            validation(user.getSecondName(), SECOND_NAME_REGEX);
            logger.info("User validation success");
        return true;
    }
    public boolean validateSignIn(User user) throws ValidateException {
        validation(user.getLogin(), LOGIN_REGEX);
        validation(user.getPassword(), PASSWORD_REGEX);
        logger.info("User sign in validation success");
        return true;
    }
    public boolean validateChangePassword(String pass,String pass2) throws ValidateException {
        validation(pass, PASSWORD_REGEX);
        validation(pass2, PASSWORD_REGEX);
        logger.info("User change password validate success");
        return true;
    }
    public boolean validation(String toValidate,String regex) throws ValidateException {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(toValidate);
        if (matcher.find()) {
            return true;
        } else {
            throw new ValidateException("Validate error: "  + toValidate);
        }
    }
}
