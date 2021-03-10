package com.tritpo.training.dao.statementsetter;

import com.tritpo.training.domain.TaskAnswerStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public enum  AnswerStatusSetter implements StatementSetter<TaskAnswerStatus> {
    INSTANCE;

    public static AnswerStatusSetter getInstance() {return INSTANCE;}
    @Override
    public PreparedStatement statementSet(String query, Connection connection, TaskAnswerStatus entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,entity.getName());
        return preparedStatement;
    }
}
