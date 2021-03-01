package com.tritpo.training.dao.statementsetter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public interface StatementSetter<T> {
    PreparedStatement statementSet(String query, Connection connection,T entity) throws SQLException;
}
