package com.tritpo.training.dao.impl;

import com.tritpo.training.dao.BaseDao;
import com.tritpo.training.dao.reader.impl.TaskAnswerReaderImpl;
import com.tritpo.training.dao.statementsetter.StatementSetter;
import com.tritpo.training.dao.statementsetter.TaskAnswerSetter;
import com.tritpo.training.domain.TaskAnswer;
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


public class TaskAnswerDaoImpl implements BaseDao<TaskAnswer> {

    private static TaskAnswerDaoImpl taskAnswerDao;

    public static TaskAnswerDaoImpl getInstance() {
        if (taskAnswerDao == null) {
            taskAnswerDao = new TaskAnswerDaoImpl();
        }

        return taskAnswerDao;
    }

    private TaskAnswerDaoImpl() {
    }


    private static final String INSERT_TASK_ANSWER = "INSERT INTO task_answer(" +
            "answer,comment,task_status_id,user_id,task_id)" +
            " VALUES(?,?,?,?,?);";
    private static final String SELECT_TASK = "SELECT * FROM task_answer ";
    private static final String UPDATE_TASK_ANSWER = "UPDATE task_answer SET " +
            " comment = ?, task_status_id = ?, user_id = ?, task_id = ? ";
    private static final String DELETE_TASK_ANSWER = "DELETE FROM task_answer ";
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger logger = LogManager.getLogger(TaskAnswerDaoImpl.class);

    @Override
    public <S extends TaskAnswer> S add(S entity)  {
        StatementSetter<TaskAnswer> statementSetter = TaskAnswerSetter.getInstance();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = statementSetter.statementSet(INSERT_TASK_ANSWER, connection, entity);
        ) {
            preparedStatement.executeUpdate();
            logger.info("Task answer added in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Cant add task answer in database");
        }
        return entity;
    }

    @Override
    public void delete(TaskAnswer entity, SqlSpecification specification){
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = specification.toPreparedStatement(connection,DELETE_TASK_ANSWER);
        ) {
            preparedStatement.executeUpdate();
            logger.info("Task answer deleted from database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Cant delete task answer from database");
        }
    }

    @Override
    public  int  update(SqlSpecification specification) {
        int i   = 0 ;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = specification.toPreparedStatement(connection,UPDATE_TASK_ANSWER);
        ) {
           i =   preparedStatement.executeUpdate();
            logger.info("Task answer updated in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Cant update task answer in database");
        }
        return  i;
    }

    @Override
    public Optional<TaskAnswer> query(SqlSpecification specification)  {
        Optional<TaskAnswer> taskAnswer = Optional.empty();
        try (Connection connection = connectionPool.getConnection();) {
            taskAnswer = (Optional<TaskAnswer>) QueryHelper.query(connection, specification, SELECT_TASK,
                    TaskAnswerReaderImpl.getInstance());
            logger.info("Task answer query executed in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Cant execute query in task answer database");
        }
        return taskAnswer;
    }

    @Override
    public List<TaskAnswer> queryAll(SqlSpecification specification){
        List<TaskAnswer> taskAnswerList = null;
        try (Connection connection = connectionPool.getConnection();) {
            taskAnswerList = (List<TaskAnswer>) QueryHelper.queryAll(connection, specification, SELECT_TASK
                    , TaskAnswerReaderImpl.getInstance());
            logger.info("Task answer query all executed in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Cant execute query all in task answer database");
        }
        return taskAnswerList;
    }
}
