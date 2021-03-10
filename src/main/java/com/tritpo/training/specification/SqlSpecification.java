package com.tritpo.training.specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public interface SqlSpecification {
    String toSQL();
    PreparedStatement toPreparedStatement(Connection connection,String sql) throws SQLException;
}
