package com.tritpo.training.dao.reader.impl;

import com.tritpo.training.dao.reader.AbstractResultSetReader;
import com.tritpo.training.domain.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public enum TaskReaderImpl implements AbstractResultSetReader<Task> {
    INSTANCE;

    public static TaskReaderImpl getInstance() {return INSTANCE;}

    @Override
    public Task oneObjectRead(ResultSet resultSet) throws SQLException {
        return Task.Builder.newInstance()
                .setId(resultSet.getInt("id"))
                .setTitle(resultSet.getString("title"))
                .setDescription(resultSet.getString("description"))
                .setDeadLine(resultSet.getTimestamp("deadLine").toLocalDateTime())
                .setCourseId(resultSet.getInt("course_id"))
                .build();
    }

    @Override
    public List<Task> manyObjectsRead(ResultSet resultSet) throws SQLException {
        List<Task> taskList = new ArrayList<>();
        while (resultSet.next()) {
            Task task = Task.Builder.newInstance()
                    .setId(resultSet.getInt("id"))
                    .setTitle(resultSet.getString("title"))
                    .setDescription(resultSet.getString("description"))
                    .setDeadLine(resultSet.getTimestamp("deadLine").toLocalDateTime())
                    .setCourseId(resultSet.getInt("course_id"))
                    .build();
            taskList.add(task);
        }
        return taskList;
    }
}
