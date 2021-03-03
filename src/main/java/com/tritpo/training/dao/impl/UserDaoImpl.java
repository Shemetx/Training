package com.tritpo.training.dao.impl;

import com.tritpo.training.dao.BaseDao;
import com.tritpo.training.dao.reader.impl.UserReaderImpl;
import com.tritpo.training.dao.statementsetter.StatementSetter;
import com.tritpo.training.dao.statementsetter.UserSetter;
import com.tritpo.training.domain.User;
import com.tritpo.training.pool.ConnectionPool;
import com.tritpo.training.specification.SqlSpecification;
import com.tritpo.training.util.QueryHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class UserDaoImpl implements BaseDao<User> {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);
    private static UserDaoImpl userDao;

    public static UserDaoImpl getInstance() {
        if (userDao == null) {
            userDao = new UserDaoImpl();
        }

        return userDao;
    }

    private UserDaoImpl() {
    }

    private static final String INSERT_USER = "INSERT INTO app_user(login,password,first_name," +
            "second_name,email,is_banned,role_id) " +
            "VALUES(?,?,?,?,?,?,?);";
    private static final String DELETE_USER = "DELETE FROM app_user ";
    private static final String UPDATE_USER = "UPDATE app_user SET " +
            " login = ?, password = ?, first_name = ?, second_name = ?, email = ?, is_banned = ?, role_id = ? ";
    private static final String SELECT_USER = "SELECT * FROM app_user ";


    @Override
    public <S extends User> S add(S entity)  {
        StatementSetter<User> setter = UserSetter.getInstance();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = setter.statementSet
                     (INSERT_USER, connection, entity);) {
            preparedStatement.executeUpdate();
            logger.info("User added in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Cant add user in database");
        }

        return entity;
    }

    @Override
    public void delete(User entity, SqlSpecification specification){
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = specification.toPreparedStatement(connection, DELETE_USER);
        ) {
            preparedStatement.executeUpdate();
            logger.info("User deleted from database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Cant delete user from database");
        }
    }

    @Override
    public int update(SqlSpecification specification)  {
        int i = 0;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = specification.toPreparedStatement(connection, UPDATE_USER);
        ) {
            i = preparedStatement.executeUpdate();
            logger.info("User updated in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Cant update user in database");
        }
        return i;
    }

    @Override
    public Optional<User> query(SqlSpecification specification)  {
        Optional<User> user = Optional.empty();
        try (Connection connection = connectionPool.getConnection();) {
            user = (Optional<User>) QueryHelper.query(connection, specification, SELECT_USER, UserReaderImpl.getInstance());
            logger.info("User database query executed successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Cant execute user database query :" + specification.getClass());
        }
        return user;
    }

    @Override
    public List<User> queryAll(SqlSpecification specification)  {
        List<User> userList = null;
        try (Connection connection = connectionPool.getConnection();) {
            userList = (List<User>) QueryHelper.queryAll(connection, specification, SELECT_USER, UserReaderImpl.getInstance());
            logger.info("User database query executed successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Cant execute user database queryAll :" + specification.getClass());
        }
        return userList;
    }

}
