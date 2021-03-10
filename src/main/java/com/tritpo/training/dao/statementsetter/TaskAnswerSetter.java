package com.tritpo.training.dao.statementsetter;

import com.tritpo.training.domain.TaskAnswer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public enum  TaskAnswerSetter implements StatementSetter<TaskAnswer>{
    INSTANCE;

    public static TaskAnswerSetter getInstance() {return INSTANCE;}

    @Override
    public PreparedStatement statementSet(String query, Connection connection, TaskAnswer entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,entity.getAnswer());
        preparedStatement.setString(2,entity.getComment());
        preparedStatement.setInt(3,entity.getStatus().getId());
        preparedStatement.setInt(4,entity.getUserId());
        preparedStatement.setInt(5,entity.getTaskId());
        return preparedStatement;
    }
}
