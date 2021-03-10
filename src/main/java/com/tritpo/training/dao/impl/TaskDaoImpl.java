package com.tritpo.training.dao.impl;

import com.tritpo.training.dao.BaseDao;
import com.tritpo.training.dao.reader.impl.TaskReaderImpl;
import com.tritpo.training.dao.statementsetter.StatementSetter;
import com.tritpo.training.dao.statementsetter.TaskSetter;
import com.tritpo.training.domain.Task;
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


public class TaskDaoImpl implements BaseDao<Task> {

    private static TaskDaoImpl taskDao;

    public static TaskDaoImpl getInstance() {
        if (taskDao == null) {
            taskDao = new TaskDaoImpl();
        }

        return taskDao;
    }

    private TaskDaoImpl() {
    }

    private static final String ADD_TASK = "INSERT INTO task(title,description,deadline,course_id) " +
            "VALUES (?,?,?,?);";
    private static final String SELECT_TASK = "SELECT * FROM task ";
    private static final String UPDATE_TASK = "UPDATE task SET " +
            "title =?, description = ?, deadline = ?,course_id = ? ";
    private static final String DELETE_TASK = "DELETE FROM task ";
    private ConnectionPool connectionPool  = ConnectionPool.getInstance();
    private static final Logger logger = LogManager.getLogger(TaskDaoImpl.class);

    @Override
    public <S extends Task> S add(S entity){
        StatementSetter<Task> statementSetter = TaskSetter.getInstance();
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = statementSetter.statementSet(ADD_TASK,connection,entity)) {
            preparedStatement.executeUpdate();
            logger.info("Task added in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Cant add task in database");
        }
        return entity;
    }

    @Override
    public void delete(Task entity, SqlSpecification specification)  {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = specification.toPreparedStatement(connection,DELETE_TASK);
        ) {
            preparedStatement.executeUpdate();
            logger.info("Task deleted from database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Cant delete task from database");
        }
    }

    @Override
    public Optional<Task> query(SqlSpecification specification)  {
        Optional<Task> task = Optional.empty();
        try (Connection connection = connectionPool.getConnection();) {
            task = (Optional<Task>) QueryHelper.query(connection, specification, SELECT_TASK, TaskReaderImpl.getInstance());
            logger.info("Task query executed in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Cant execute query in task database");
        }
        return task;
    }

    @Override
    public List<Task> queryAll(SqlSpecification sqlSpecification)  {
        List<Task> taskList = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();) {
            taskList = (List<Task>) QueryHelper.queryAll(connection, sqlSpecification, SELECT_TASK, TaskReaderImpl.getInstance());
            logger.info("Task executed query all in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Cant execute query all in task database");
        }

        return taskList;
    }

    @Override
    public  int update(SqlSpecification specification)  {
        int i = 0;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = specification.toPreparedStatement(connection,UPDATE_TASK);
        ) {
          i =   preparedStatement.executeUpdate();
            logger.info("Task updated in database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Cant update task in database");
        }
        return i;
    }
}
