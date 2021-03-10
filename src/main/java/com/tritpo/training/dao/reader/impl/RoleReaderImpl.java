package com.tritpo.training.dao.reader.impl;

import com.tritpo.training.dao.reader.AbstractResultSetReader;
import com.tritpo.training.domain.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public enum RoleReaderImpl implements AbstractResultSetReader<Role> {
    INSTANCE;

    public static RoleReaderImpl getInstance() {return INSTANCE;}

    @Override
    public Role oneObjectRead(ResultSet resultSet) throws SQLException {
        return Role.resolveById(resultSet.getInt("id"));
    }

    @Override
    public List<Role> manyObjectsRead(ResultSet resultSet) throws SQLException {
        List<Role> roleList = new ArrayList<>();
        while (resultSet.next()) {
            Role role = Role.resolveById(resultSet.getInt("id"));
            roleList.add(role);
        }
        return roleList;
    }
}
