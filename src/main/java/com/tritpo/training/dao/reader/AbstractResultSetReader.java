package com.tritpo.training.dao.reader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public interface AbstractResultSetReader<T> {

    T oneObjectRead(ResultSet resultSet) throws SQLException;

    List<T> manyObjectsRead(ResultSet resultSet) throws SQLException;
}
