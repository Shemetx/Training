package com.tritpo.training.dao.statementsetter;

import com.tritpo.training.domain.Schedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;


public enum  ScheduleSetter implements StatementSetter<Schedule>{
    INSTANCE;
    public static ScheduleSetter getInstance() {return INSTANCE; }

    @Override
    public PreparedStatement statementSet(String query, Connection connection, Schedule entity) throws SQLException {
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setTime(1, Time.valueOf(entity.getStartTime()));
      preparedStatement.setInt(2,entity.getDayOfWeek().getId());
        return preparedStatement;
    }
}
