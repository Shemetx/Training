package com.tritpo.training.dao.impl;

import com.tritpo.training.dao.BaseDao;
import com.tritpo.training.dao.reader.impl.DayOfWeekReaderImpl;
import com.tritpo.training.dao.statementsetter.DayOfWeekSetter;
import com.tritpo.training.dao.statementsetter.StatementSetter;
import com.tritpo.training.domain.DayOfWeek;
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


public class DayOfWeekDaoImpl implements BaseDao<DayOfWeek> {
    private static DayOfWeekDaoImpl dayOfWeekDao;

    public static DayOfWeekDaoImpl getInstance() {
        if (dayOfWeekDao == null) {
            dayOfWeekDao = new DayOfWeekDaoImpl();
        }

        return dayOfWeekDao;
    }

    private DayOfWeekDaoImpl() {
    }
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger logger = LogManager.getLogger(DayOfWeekDaoImpl.class);
    private static final String ADD_DAY_OF_WEEK = "INSERT INTO day_of_week(day_of_week) " +
            "VALUES (?);";
    private static final String SELECT_DAY_OF_WEEK = "SELECT * day_of_week ";
    private static final String UPDATE_DAY_OF_WEEK = "UPDATE day_of_week SET " +
            " day = ?";
    private static final String DELETE_DAY_OF_WEEK = "DELETE FROM day_of_week ";

    @Override
    public <S extends DayOfWeek> S add(S entity) {
        StatementSetter<DayOfWeek> setter = DayOfWeekSetter.getInstance() ;
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = setter.statementSet(ADD_DAY_OF_WEEK,connection,entity))
        {
            preparedStatement.executeUpdate();
            logger.info("Day of week added in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Cant add day of week in database");
        }
        return null;
    }

    @Override
    public void delete(DayOfWeek entity, SqlSpecification specification) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = specification.toPreparedStatement(connection,DELETE_DAY_OF_WEEK);
        ) {
            preparedStatement.executeUpdate();
            logger.info("Day of week deleted from database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Cant delete Day of week from database");
        }
    }

    @Override
    public  int update( SqlSpecification specification)  {
        int i  = 0 ;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = specification.toPreparedStatement(connection,UPDATE_DAY_OF_WEEK);
        ) {
         i =    preparedStatement.executeUpdate();
            logger.info("Day of week updated in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Cant update day of week in database");
        }
        return i;
    }

    @Override
    public Optional<DayOfWeek> query(SqlSpecification specification)  {
        Optional<DayOfWeek> dayOfWeek = Optional.empty();
        try (Connection connection = connectionPool.getConnection();) {
            dayOfWeek = (Optional<DayOfWeek>) QueryHelper.query(connection, specification,
                    SELECT_DAY_OF_WEEK, DayOfWeekReaderImpl.getInstance());
            logger.info("Day of week query executed in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.info("Cant execute query in day of week database successfully");
        }
        return dayOfWeek;
    }

    @Override
    public List<DayOfWeek> queryAll(SqlSpecification sqlSpecification) {
        List<DayOfWeek> dayOfWeekList = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();) {
            dayOfWeekList = (List<DayOfWeek>) QueryHelper.queryAll(connection, sqlSpecification,
                    SELECT_DAY_OF_WEEK, DayOfWeekReaderImpl.getInstance());
            logger.info("Day of week query all executed in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.info("Cant execute query all in day of week database successfully");
        }
        return dayOfWeekList;
    }

}
