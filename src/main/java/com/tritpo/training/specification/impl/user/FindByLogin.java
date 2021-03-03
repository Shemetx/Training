package com.tritpo.training.specification.impl.user;


import com.tritpo.training.specification.SqlSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindByLogin implements SqlSpecification {
    private String login;

    public FindByLogin(String login) {
        this.login = login;
    }

    @Override
    public String toSQL() {
        return " WHERE login = ?";
    }

    @Override
    public PreparedStatement toPreparedStatement(Connection connection, String sql) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql + toSQL());
        preparedStatement.setString(1,login);
        return preparedStatement;
    }


}
