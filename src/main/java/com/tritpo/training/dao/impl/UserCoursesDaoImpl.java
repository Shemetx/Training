package com.tritpo.training.dao.impl;

import com.tritpo.training.dao.BaseDao;
import com.tritpo.training.dao.reader.impl.UserCoursesReaderImpl;
import com.tritpo.training.domain.ManyToManyEntity;
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


public class UserCoursesDaoImpl implements BaseDao<ManyToManyEntity> {

    private static UserCoursesDaoImpl userCoursesDao;

    public static UserCoursesDaoImpl getInstance() {
        if (userCoursesDao == null) {
            userCoursesDao = new UserCoursesDaoImpl();
        }

        return userCoursesDao;
    }

    private UserCoursesDaoImpl() {
    }
    private static final String INSERT_USER_COURSE = "INSERT INTO users_courses(user_id, course_id,user_role_id)" +
            " VALUES(?,?,?);";
    private static final String SELECT_USER_COURSE = "SELECT * FROM users_courses ";
    private static final String UPDATE_USER_COURSE = "UPDATE users_courses SET " +
            "user_id = ?, course_id = ?, user_role_id = ? ";
    private static final String DELETE_USER_COURSE  = "DELETE FROM users_courses ";
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger logger = LogManager.getLogger(UserCoursesDaoImpl.class);

    @Override
    public <S extends ManyToManyEntity> S add(S entity) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_COURSE);
        ) {
            preparedStatement.setInt(1,entity.getFirstID());
            preparedStatement.setInt(2,entity.getSecondID());
            preparedStatement.setInt(3,entity.getRole().getId());
            preparedStatement.executeUpdate();
            logger.info("User courses add in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Cant add user courses in database");
        }
        return entity;
    }

    @Override
    public void delete(ManyToManyEntity entity, SqlSpecification specification) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = specification.toPreparedStatement(connection,DELETE_USER_COURSE);
        ) {
            preparedStatement.executeUpdate();
            logger.info("User courses deleted from database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Cant delete user courses in database");
        }
    }

    @Override
    public int update(SqlSpecification specification)  {
        int i = 0;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = specification.toPreparedStatement(connection, UPDATE_USER_COURSE);
        ) {
            i =   preparedStatement.executeUpdate();
            logger.info("User courses updated in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Cant update user courses in database");
        }
        return i;
    }

    @Override
    public Optional<ManyToManyEntity> query(SqlSpecification specification)  {
        Optional<ManyToManyEntity> entity = Optional.empty();
        try(Connection connection = connectionPool.getConnection();) {
            entity = (Optional<ManyToManyEntity>) QueryHelper.query(connection,specification,SELECT_USER_COURSE, UserCoursesReaderImpl.getInstance());
            logger.info("User courses query executed in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Cant execute query user courses in database");
        }
        return  entity;
    }

    @Override
    public List<ManyToManyEntity> queryAll(SqlSpecification specification)  {
        List<ManyToManyEntity> list = null;
        try(Connection connection = connectionPool.getConnection();) {
            list = (List<ManyToManyEntity>) QueryHelper.queryAll(connection,specification,SELECT_USER_COURSE, UserCoursesReaderImpl.getInstance());
            logger.info("User courses query all executed in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Cant execute queryAll user courses in database");
        }
        return list;
    }
}
