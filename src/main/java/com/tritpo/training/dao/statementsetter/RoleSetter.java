package com.tritpo.training.dao.statementsetter;

import com.tritpo.training.domain.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public enum RoleSetter implements StatementSetter<Role>{
    INSTANCE;
    public static RoleSetter getInstance() {return  INSTANCE;}

    @Override
    public PreparedStatement statementSet(String query, Connection connection, Role entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,entity.getName());
        return preparedStatement;
    }
}
