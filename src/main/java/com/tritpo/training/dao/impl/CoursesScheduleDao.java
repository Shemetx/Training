package com.tritpo.training.dao.impl;

import com.tritpo.training.dao.BaseDao;
import com.tritpo.training.dao.reader.impl.ManyToManyReaderImpl;
import com.tritpo.training.domain.ManyToManyEntity;
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


public class CoursesScheduleDao implements BaseDao<ManyToManyEntity> {

    private static CoursesScheduleDao coursesScheduleDao;

    public static CoursesScheduleDao getInstance() {
        if (coursesScheduleDao == null) {
            coursesScheduleDao = new CoursesScheduleDao();
        }

        return coursesScheduleDao;
    }

    private CoursesScheduleDao() {
    }


    private static final String INSERT_COURSE_SCHEDULE = "INSERT INTO courses_schedule(schedule_id, course_id)" +
            " VALUES(?,?);";
    private static final String SELECT_COURSE_SCHEDULE = "SELECT * FROM courses_schedule ";
    private static final String UPDATE_COURSE_SCHEDULE = "UPDATE courses_schedule SET " +
            "schedule_id = ?, course_id = ? ";
    private static final String DELETE_COURSE_SCHEDULE = "DELETE FROM courses_schedule ";
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger logger = LogManager.getLogger(CoursesScheduleDao.class);

    @Override
    public <S extends ManyToManyEntity> S add(S entity) {

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COURSE_SCHEDULE);
        ) {
            preparedStatement.setInt(1, entity.getFirstID());
            preparedStatement.setInt(2, entity.getSecondID());
            preparedStatement.executeUpdate();
            logger.info("Course schedule  added in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.info("Cant add course schedule in database successfully");
        }
        return entity;
    }



    @Override
    public void delete(ManyToManyEntity entity, SqlSpecification specification) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = specification.toPreparedStatement(connection, DELETE_COURSE_SCHEDULE);
        ) {
            preparedStatement.executeUpdate();
            logger.info("Course schedule deleted from database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.info("Cant delete course schedule in database");
        }
    }

    @Override
    public int update(SqlSpecification specification)  {
        int i = 0;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = specification.toPreparedStatement(connection, UPDATE_COURSE_SCHEDULE);
        ) {
            i = preparedStatement.executeUpdate();
            logger.info("Course schedule updated in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.info("Cant update course schedule in database");
        }
        return i;
    }

    @Override
    public Optional<ManyToManyEntity> query(SqlSpecification specification) {
        Optional<ManyToManyEntity> entity = Optional.empty();
        try (Connection connection = connectionPool.getConnection();) {
            entity = (Optional<ManyToManyEntity>) QueryHelper.query(connection, specification, SELECT_COURSE_SCHEDULE, ManyToManyReaderImpl.getInstance());
            logger.info("Course schedule query executed in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.info("Cant execute query course schedule in database");
        }
        return entity;
    }

    @Override
    public List<ManyToManyEntity> queryAll(SqlSpecification specification) {
        List<ManyToManyEntity> list = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();) {
            list = (List<ManyToManyEntity>) QueryHelper.queryAll(connection, specification, SELECT_COURSE_SCHEDULE, ManyToManyReaderImpl.getInstance());
            logger.info("Course schedule query all executed in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.info("Cant execute query all course schedule in database");
        }
        return list;
    }
}
