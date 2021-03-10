package com.tritpo.training.dao.impl;

import com.tritpo.training.dao.BaseDao;
import com.tritpo.training.dao.reader.impl.AnswerStatusReaderImpl;
import com.tritpo.training.dao.statementsetter.AnswerStatusSetter;
import com.tritpo.training.dao.statementsetter.StatementSetter;
import com.tritpo.training.domain.TaskAnswerStatus;
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


public class TaskAnswerStatusDaoImpl implements BaseDao<TaskAnswerStatus> {

    private static TaskAnswerStatusDaoImpl taskAnswerStatusDao;

    public static TaskAnswerStatusDaoImpl getInstance() {
        if (taskAnswerStatusDao == null) {
            taskAnswerStatusDao = new TaskAnswerStatusDaoImpl();
        }

        return taskAnswerStatusDao;
    }

    private TaskAnswerStatusDaoImpl() {
    }
    private static final String ADD_ANSWER_STATUS = "INSERT INTO task_answer_status" +
            "(id,status)" +
            " VALUES(?,?);";
    private static final String SELECT_ANSWER_STATUS = "SELECT * FROM task_answer_status ";
    private static final String UPDATE_ANSWER_STATUS = "UPDATE task_answer_status SET " +
            "id = ?, status = ? ";
    private static final String DELETE_ANSWER_STATUS = "DELETE FROM task_answer_status ";
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger logger = LogManager.getLogger(TaskAnswerStatusDaoImpl.class);

    @Override
    public <S extends TaskAnswerStatus> S add(S entity)  {
        StatementSetter<TaskAnswerStatus> setter = AnswerStatusSetter.getInstance();
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = setter.statementSet(ADD_ANSWER_STATUS,connection,entity)) {
            preparedStatement.executeUpdate();
            logger.info("Task answer status added in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Cant add task answer status in database");
        }
        return null;
    }

    @Override
    public void delete(TaskAnswerStatus entity, SqlSpecification specification)  {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = specification.toPreparedStatement(connection,DELETE_ANSWER_STATUS);
        ) {
            preparedStatement.executeUpdate();
            logger.info("Task answer status deleted from database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Cant delete task answer status from database");
        }
    }

    @Override
    public int  update(SqlSpecification specification) {
        int i = 0;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = specification.toPreparedStatement(connection,UPDATE_ANSWER_STATUS);
        ) {
         i  =   preparedStatement.executeUpdate();
            logger.info("Task answer status updated in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Cant updated task answer status in database");
        }
        return i;
    }

    @Override
    public Optional<TaskAnswerStatus> query(SqlSpecification specification)  {
        Optional<TaskAnswerStatus> taskAnswer = Optional.empty();
        try (Connection connection = connectionPool.getConnection();) {
            taskAnswer = (Optional<TaskAnswerStatus>) QueryHelper.query(connection, specification, SELECT_ANSWER_STATUS, AnswerStatusReaderImpl.getInstance());
            logger.info("Task answer status query executed in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.info("Cant execute task answer status query in database successfully");
        }
        return taskAnswer;
    }

    @Override
    public List<TaskAnswerStatus> queryAll(SqlSpecification sqlSpecification)  {
        List<TaskAnswerStatus> taskList = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();) {
            taskList = (List<TaskAnswerStatus>) QueryHelper.queryAll(connection, sqlSpecification, SELECT_ANSWER_STATUS, AnswerStatusReaderImpl.getInstance());
            logger.info("Task answer status query all executed in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.info("Cant execute Task answer status query all in database successfully");
        }

        return taskList;
    }

}
