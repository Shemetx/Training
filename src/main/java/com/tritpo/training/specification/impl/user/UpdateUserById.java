package com.tritpo.training.specification.impl.user;

import com.tritpo.training.dao.statementsetter.UserSetter;
import com.tritpo.training.domain.User;
import com.tritpo.training.specification.SqlSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UpdateUserById implements SqlSpecification {
    private User user;

    public UpdateUserById(User user) {
        this.user = user;
    }

    @Override
    public String toSQL() {
        return "WHERE id = ?";
    }

    @Override
    public PreparedStatement toPreparedStatement(Connection connection, String sql) throws SQLException {
        PreparedStatement preparedStatement = UserSetter.getInstance()
                .statementSet(sql+toSQL(),connection,user);
                connection.prepareStatement(sql + toSQL());
                preparedStatement.setInt(8,user.getId());
        return preparedStatement;
    }
}
