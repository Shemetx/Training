package com.tritpo.training.dao.reader.impl;

import com.tritpo.training.dao.reader.AbstractResultSetReader;
import com.tritpo.training.domain.Role;
import com.tritpo.training.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public enum UserReaderImpl implements AbstractResultSetReader<User> {

    INSTANCE;
    public static UserReaderImpl getInstance() {return INSTANCE;}
    @Override
    public User oneObjectRead(ResultSet resultSet) throws SQLException {
        return User.Builder.newInstance()
                .setId(resultSet.getInt("id"))
                .setLogin(resultSet.getString("login"))
                .setPassword(resultSet.getString("password"))
                .setFirstName(resultSet.getString("first_name"))
                .setSecondName(resultSet.getString("second_name"))
                .setEmail(resultSet.getString("email"))
                .setRole(Role.resolveById(resultSet.getInt("role_id")))
                .setIsBanned(resultSet.getBoolean("is_banned"))
                .build();
    }

    @Override
    public List<User> manyObjectsRead(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            User user = User.Builder.newInstance()
                    .setId(resultSet.getInt("id"))
                    .setLogin(resultSet.getString("login"))
                    .setPassword(resultSet.getString("password"))
                    .setFirstName(resultSet.getString("first_name"))
                    .setSecondName(resultSet.getString("second_name"))
                    .setEmail(resultSet.getString("email"))
                    .setRole(Role.resolveById(resultSet.getInt("role_id")))
                    .setIsBanned(resultSet.getBoolean("is_banned"))
                    .build();
            users.add(user);
        }
        return users;
    }
}
