package com.tritpo.training.dao.impl;

import com.tritpo.training.dao.BaseDao;
import com.tritpo.training.dao.reader.impl.CourseReaderImpl;
import com.tritpo.training.dao.statementsetter.CourseSetter;
import com.tritpo.training.dao.statementsetter.StatementSetter;
import com.tritpo.training.domain.Course;
import com.tritpo.training.pool.ConnectionPool;
import com.tritpo.training.specification.SqlSpecification;
import com.tritpo.training.util.LastInsertDao;
import com.tritpo.training.util.QueryHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class CourseDaoImpl implements BaseDao<Course> {

    private static CourseDaoImpl courseDao;

    public static CourseDaoImpl getInstance() {
        if (courseDao == null) {
            courseDao = new CourseDaoImpl();
        }

        return courseDao;
    }

    private CourseDaoImpl() {
    }

    private static final Logger logger = LogManager.getLogger(CourseDaoImpl.class);
    private static final String INSERT_COURSE = "INSERT INTO course(start_date, title, description)" +
            "VALUES(?,?,?);";
    private static final String DELETE_COURSE = "DELETE FROM course ";
    private static final String SELECT_COURSE = "SELECT * FROM course ";
    private static final String UPDATE_COURSE = "UPDATE course SET " +
            "start_date = ?, title = ?, description = ? ";
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public <S extends Course> S add(S entity)  {
        StatementSetter<Course> statementSetter = CourseSetter.getInstance();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = statementSetter.statementSet(INSERT_COURSE, connection, entity);
        ) {
            preparedStatement.executeUpdate();
            entity.setId(LastInsertDao.getLastDao(connection));
            logger.info("Course added in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.info("Cant add course in database successfully");
        }
        return entity;
    }

    @Override
    public void delete(Course entity, SqlSpecification specification){
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = specification.toPreparedStatement(connection, DELETE_COURSE);
        ) {
            preparedStatement.executeUpdate();
            logger.info("Course deleted from database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.info("Cant delete course from database successfully");
        }
    }

    @Override
    public int update(SqlSpecification specification) {
        int i = 0;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = specification.toPreparedStatement(connection, UPDATE_COURSE);
        ) {
            i = preparedStatement.executeUpdate();
            logger.info("Course updated in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.info("Cant update course in database successfully");
        }
        return i;
    }

    @Override
    public Optional<Course> query(SqlSpecification specification) {
        Optional<Course> course = Optional.empty();
        try (Connection connection = connectionPool.getConnection();) {
            course = (Optional<Course>) QueryHelper.query(connection, specification, SELECT_COURSE, CourseReaderImpl.getInstance());
            logger.info("Course query executed in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.info("Cant execute query in course database");
        }
        return course;
    }

    @Override
    public List<Course> queryAll(SqlSpecification sqlSpecification){
        List<Course> courseList = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();) {
            courseList = (List<Course>) QueryHelper.queryAll(connection, sqlSpecification, SELECT_COURSE, CourseReaderImpl.getInstance());
            logger.info("Course query all executed in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.info("Cant execute query all in course database");
        }

        return courseList;
    }

}


