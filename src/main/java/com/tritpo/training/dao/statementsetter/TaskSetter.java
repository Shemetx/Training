package com.tritpo.training.dao.statementsetter;

import com.tritpo.training.domain.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;


public enum TaskSetter implements StatementSetter<Task> {
    INSTANCE;

    public static TaskSetter getInstance() {return  INSTANCE;}

    @Override
    public PreparedStatement statementSet(String query, Connection connection, Task entity) throws SQLException {
       PreparedStatement preparedStatement = connection.prepareStatement(query);
       preparedStatement.setString(1,entity.getTitle());
       preparedStatement.setString(2,entity.getDescription());
       preparedStatement.setTimestamp(3, Timestamp.valueOf(entity.getDeadLine()));
       preparedStatement.setInt(4,entity.getCourse_id());
       return preparedStatement;
    }
}
