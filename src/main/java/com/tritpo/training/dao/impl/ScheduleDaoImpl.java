package com.tritpo.training.dao.impl;

import com.tritpo.training.dao.BaseDao;
import com.tritpo.training.dao.reader.impl.ScheduleReaderImpl;
import com.tritpo.training.dao.statementsetter.ScheduleSetter;
import com.tritpo.training.dao.statementsetter.StatementSetter;
import com.tritpo.training.domain.Schedule;
import com.tritpo.training.pool.ConnectionPool;
import com.tritpo.training.specification.SqlSpecification;
import com.tritpo.training.util.LastInsertDao;
import com.tritpo.training.util.QueryHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class ScheduleDaoImpl implements BaseDao<Schedule> {
    private static ScheduleDaoImpl scheduleDao;

    public static ScheduleDaoImpl getInstance() {
        if (scheduleDao == null) {
            scheduleDao = new ScheduleDaoImpl();
        }

        return scheduleDao;
    }

    private ScheduleDaoImpl() {
    }

    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger logger = LogManager.getLogger(ScheduleDaoImpl.class);
    private static final String ADD_SCHEDULE = "INSERT INTO schedule(time, day_id) " +
            "VALUES(?,?) ";
    private static final String SELECT_SCHEDULE = "SELECT * FROM schedule ";
    private static final String UPDATE_SCHEDULE = "UPDATE schedule SET " +
            " time = ?, day_id = ? ";
    private static final String DELETE_SCHEDULE = "DELETE FROM schedule ";

    @Override
    public <S extends Schedule> S add(S entity) {
        StatementSetter<Schedule> setter = ScheduleSetter.getInstance();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = setter.statementSet(ADD_SCHEDULE, connection, entity);) {
            preparedStatement.executeUpdate();
            entity.setId(LastInsertDao.getLastDao(connection));
            logger.info("Schedule added in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Cant add schedule in database");
        }
        return entity;
    }

    @Override
    public void delete(Schedule entity, SqlSpecification specification){
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = specification.toPreparedStatement(connection,DELETE_SCHEDULE);
        ) {
            preparedStatement.executeUpdate();
            logger.info("Schedule deleted from database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Cant delete schedule from database");
        }
    }

    @Override
    public int update(SqlSpecification specification) {
        int i = 0;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = specification.toPreparedStatement(connection, UPDATE_SCHEDULE);
        ) {
            i = preparedStatement.executeUpdate();
            logger.info("Schedule updated in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Cant update schedule in database");
        }
        return i;
    }

    @Override
    public Optional<Schedule> query(SqlSpecification specification)  {
        Optional<Schedule> schedule = Optional.empty();
        try (Connection connection = connectionPool.getConnection();) {
            schedule = (Optional<Schedule>) QueryHelper.query(connection, specification, SELECT_SCHEDULE,
                    ScheduleReaderImpl.getInstance());
            logger.info("Schedule query executed in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Cant execute query schedule in database");
        }
        return schedule;
    }

    @Override
    public List<Schedule> queryAll(SqlSpecification specification) {
        List<Schedule> scheduleList = null;
        try (Connection connection = connectionPool.getConnection();) {
            scheduleList = (List<Schedule>) QueryHelper.queryAll(connection, specification, SELECT_SCHEDULE
                    , ScheduleReaderImpl.getInstance());
            logger.info("Schedule query all executed in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Cant execute query all schedule in database");
        }
        return scheduleList;
    }

}
