package com.tritpo.training.dao.statementsetter;


import com.tritpo.training.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public enum UserSetter implements StatementSetter<User> {
    INSTANCE;

    public static UserSetter getInstance() {return INSTANCE;}

    @Override
    public PreparedStatement statementSet(String query, Connection connection, User entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,entity.getLogin());
        preparedStatement.setString(2, entity.getPassword());
        preparedStatement.setString(3,entity.getFirstName());
        preparedStatement.setString(4,entity.getSecondName());
        preparedStatement.setString(5,entity.getEmail());
        preparedStatement.setBoolean(6,entity.isBanned());
        preparedStatement.setInt(7,entity.getRole().getId());
        return preparedStatement;
    }
}
