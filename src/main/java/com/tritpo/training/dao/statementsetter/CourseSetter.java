package com.tritpo.training.dao.statementsetter;

import com.tritpo.training.domain.Course;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public enum CourseSetter implements StatementSetter<Course>{
    INSTANCE;

    public static CourseSetter getInstance() {return INSTANCE; }

    @Override
    public PreparedStatement statementSet(String query, Connection connection, Course entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setDate(1, Date.valueOf(entity.getStartDate()));
        preparedStatement.setString(2,entity.getTitle());
        preparedStatement.setString(3,entity.getDescription());
        return preparedStatement;
    }
}
