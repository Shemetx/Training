package com.tritpo.training.dao.statementsetter;

import com.tritpo.training.domain.DayOfWeek;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public enum DayOfWeekSetter implements StatementSetter<DayOfWeek>{
    INSTANCE;

    public static DayOfWeekSetter getInstance() {return INSTANCE;}


    @Override
    public PreparedStatement statementSet(String query, Connection connection, DayOfWeek entity) throws SQLException {
       PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,entity.getName());
        return preparedStatement;
    }
}
