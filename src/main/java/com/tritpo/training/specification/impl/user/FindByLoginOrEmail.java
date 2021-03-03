package com.tritpo.training.specification.impl.user;


import com.tritpo.training.specification.SqlSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class FindByLoginOrEmail implements SqlSpecification {
    private String login;
    private String email;

    public FindByLoginOrEmail(String login, String email) {
        this.login = login;
        this.email = email;
    }

    @Override
    public String toSQL() {
        return " WHERE login = ? OR email = ?";
    }

    @Override
    public PreparedStatement toPreparedStatement(Connection connection, String sql) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql + toSQL());
        preparedStatement.setString(1,login);
        preparedStatement.setString(2,email);
        return preparedStatement;
    }
}
