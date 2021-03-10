package com.tritpo.training.specification.impl;

import com.tritpo.training.specification.SqlSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class FindAll implements SqlSpecification {


    @Override
    public String toSQL() {
        return "";
    }

    @Override
    public PreparedStatement toPreparedStatement(Connection connection, String sql) throws SQLException {
        return connection.prepareStatement(sql + toSQL());
    }
}
