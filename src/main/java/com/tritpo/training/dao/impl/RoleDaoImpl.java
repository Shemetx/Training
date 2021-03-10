package com.tritpo.training.dao.impl;

import com.tritpo.training.dao.BaseDao;
import com.tritpo.training.dao.reader.impl.RoleReaderImpl;
import com.tritpo.training.dao.statementsetter.RoleSetter;
import com.tritpo.training.dao.statementsetter.StatementSetter;
import com.tritpo.training.domain.Role;
import com.tritpo.training.pool.ConnectionPool;
import com.tritpo.training.specification.SqlSpecification;
import com.tritpo.training.util.QueryHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class RoleDaoImpl implements BaseDao<Role> {


    private static RoleDaoImpl roleDao;

    public static RoleDaoImpl getInstance() {
        if (roleDao == null) {
            roleDao = new RoleDaoImpl();
        }

        return roleDao;
    }

    private RoleDaoImpl() {
    }

    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger logger = LogManager.getLogger(RoleDaoImpl.class);
    private static final String INSERT_ROLE = "INSERT INTO user_role(role) " +
            "VALUES(?);";
    private static final String DELETE_ROLE = "DELETE FROM user_role ";
    private static final String SELECT_ROLE = "SELECT * FORM user_role ";
    private static final String UPDATE_ROLE = "UPDATE user_role SET " +
            " role = ? ";

    @Override
    public <S extends Role> S add(S entity)  {
        StatementSetter<Role> setter = RoleSetter.getInstance();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = setter.statementSet(INSERT_ROLE, connection, entity)) {
            preparedStatement.executeUpdate();
            logger.info("Role added in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Cant add role in database");
        }
        return entity;
    }

    @Override
    public void delete(Role entity, SqlSpecification specification) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = specification.toPreparedStatement(connection,DELETE_ROLE);
        ) {
            preparedStatement.executeUpdate();
            logger.info("Role deleted from database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Cant delete role from database");
        }
    }

    @Override
    public Optional<Role> query(SqlSpecification specification) {
        Optional<Role> role = Optional.empty();
        try (Connection connection = connectionPool.getConnection();) {
            role = (Optional<Role>) QueryHelper.query(connection, specification, SELECT_ROLE, RoleReaderImpl.getInstance());
            logger.info("Role query executed in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Cant execute query role in database");
        }
        return role;
    }

    @Override
    public List<Role> queryAll(SqlSpecification sqlSpecification) {
        List<Role> roleList = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();) {
            roleList = (List<Role>) QueryHelper.queryAll(connection, sqlSpecification, SELECT_ROLE, RoleReaderImpl.getInstance());
            logger.info("Role query all executed in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Cant execute query all role in database");
        }
        return roleList;
    }

    @Override
    public int update(SqlSpecification specification)  {
        int i = 0;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = specification.toPreparedStatement(connection, UPDATE_ROLE);
        ) {
            i = preparedStatement.executeUpdate();
            logger.info("Role updated in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Cant update role in database");
        }
        return i;
    }
}
