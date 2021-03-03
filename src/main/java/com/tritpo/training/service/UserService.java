package com.tritpo.training.service;


import com.google.protobuf.ServiceException;
import com.tritpo.training.context.impl.TrainingContext;
import com.tritpo.training.dao.impl.UserDaoImpl;
import com.tritpo.training.domain.User;
import com.tritpo.training.exception.ValidateException;
import com.tritpo.training.specification.impl.user.FindByLogin;
import com.tritpo.training.specification.impl.user.FindByLoginOrEmail;
import com.tritpo.training.util.PasswordHash;
import com.tritpo.training.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Collection;
import java.util.Optional;


public class UserService {

    /**
     * cache instance of {@link TrainingContext}
     */
    private TrainingContext context = TrainingContext.getInstance();
    /**
     * collection of users
     */
    private Collection<User> usersCollection = context.retrieveBaseEntityList(User.class);
    private static final Logger logger = LogManager.getLogger(UserService.class);
    private static UserService userService;

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    private UserService() {
    }

    /**
     * instance to work with dao layer
     */
    private UserDaoImpl userDao = UserDaoImpl.getInstance();

    /**
     * Method responsible for sign in user depends on login and password.
     *
     * @param user is a {@link User} entity that contains user necessary information
     * @return a {@link Optional} object with {@link User} inside.
     */
    public Optional<User> signIn(User user) {

        try {
            UserValidator.getInstance().validateSignIn(user);

            Optional<User> findUser = findByLogin(user.getLogin());

            boolean match = PasswordHash.validatePassword(user.getPassword(), findUser.get().getPassword());
            return match ? findUser : Optional.empty();

        } catch (ServiceException | ValidateException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            logger.warn("User sign in failed");
        }
        return Optional.empty();
    }

    /**
     * Method responsible for register user
     *
     * @param user is a {@link User} entity that contains user necessary information
     * @return a {@link Optional} object with {@link User} inside.
     */
    public Optional<User> signUp(User user) {

        try {
            UserValidator.getInstance().validate(user);
            String generatePassword = PasswordHash.generateStrongPasswordHash(user.getPassword());
            user.setPassword(generatePassword);

            Optional<User> byLoginEmail = findByLoginEmail(user.getLogin(), user.getEmail());

            if (byLoginEmail.isEmpty()) {
                User add = userDao.add(user);
                user.setId(findByLogin(user.getLogin()).get().getId());
                usersCollection.add(user);
                logger.info("User sign up successfully");
                return Optional.of(add);
            }

        } catch (ValidateException | NoSuchAlgorithmException | InvalidKeySpecException | ServiceException e) {
            e.printStackTrace();// для лога
            logger.warn("User sign up failed");
        }

        return Optional.empty();

    }

    /**
     * to find {@link User} in cache by required login and email
     *
     * @param login user's login
     * @param email user's email
     * @return a {@link Optional} object with {@link User} inside.
     */
    public Optional<User> findByLoginEmailCache(String login, String email) {
        return usersCollection.stream()
                .filter(temp -> temp.getLogin().equals(login) ||
                        temp.getEmail().equals(email))
                .findFirst();
    }

    /**
     * to find {@link User} in cache and database by required login or email
     *
     * @param login user's login
     * @param email user's email
     * @return a {@link Optional} object with {@link User} inside.
     */
    public Optional<User> findByLoginEmail(String login, String email) {
        Optional<User> user = findByLoginEmailCache(login, email);
        if (user.isEmpty()) {
            user = userDao.query(new FindByLoginOrEmail(login, email));
            if (user.isEmpty()) {
                return Optional.empty();
            }
        }
        return user;
    }

    /**
     * to find {@link User} in cache by required login
     *
     * @param login user's login
     * @return a {@link Optional} object with {@link User} inside.
     */
    public Optional<User> findByLoginCache(String login) {
        return usersCollection.stream()
                .filter(temp -> temp.getLogin().equals(login))
                .findFirst();
    }

    /**
     * to find {@link User} in cache and database by required login
     *
     * @param login user's login
     * @return a {@link Optional} object with {@link User} inside.
     * @throws ServiceException if user not found by login
     */
    public Optional<User> findByLogin(String login) throws ServiceException {
        Optional<User> byIdCache = findByLoginCache(login);
        if (byIdCache.isEmpty()) {
            Optional<User> query = userDao.query(new FindByLogin(login));
            if (query.isEmpty()) {
                throw new ServiceException("Can't find user by login: " + login);
            }
            return query;
        }
        return byIdCache;
    }

}
