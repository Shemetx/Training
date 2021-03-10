package com.tritpo.training.specification.impl.user;

import com.tritpo.training.specification.SqlSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class FindByEmail implements SqlSpecification {
    private String email;

    public FindByEmail(String email) {
        this.email = email;
    }

    @Override
    public String toSQL() {
        return " WHERE email = ? ";
    }

    @Override
    public PreparedStatement toPreparedStatement(Connection connection, String sql) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql + toSQL());
        preparedStatement.setString(1,email);
        return preparedStatement;
    }
}
