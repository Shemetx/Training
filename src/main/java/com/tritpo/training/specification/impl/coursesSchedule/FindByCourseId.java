package com.tritpo.training.specification.impl.coursesSchedule;

import com.tritpo.training.specification.SqlSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class FindByCourseId implements SqlSpecification {
    private  int course_id;

    public FindByCourseId(int course_id) {
        this.course_id = course_id;
    }

    @Override
    public String toSQL() {
        return " WHERE course_id = ? ";
    }

    @Override
    public PreparedStatement toPreparedStatement(Connection connection, String sql) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql + toSQL());
        preparedStatement.setInt(1,course_id);
        return preparedStatement;
    }
}
