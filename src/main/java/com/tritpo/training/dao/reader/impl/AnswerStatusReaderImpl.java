package com.tritpo.training.dao.reader.impl;

import com.tritpo.training.dao.reader.AbstractResultSetReader;
import com.tritpo.training.domain.TaskAnswerStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public enum  AnswerStatusReaderImpl implements AbstractResultSetReader<TaskAnswerStatus> {
    INSTANCE;

    public static AnswerStatusReaderImpl getInstance() {return  INSTANCE;}

    @Override
    public TaskAnswerStatus oneObjectRead(ResultSet resultSet) throws SQLException {
        return TaskAnswerStatus.resolveById(resultSet.getInt("id"));
    }

    @Override
    public List<TaskAnswerStatus> manyObjectsRead(ResultSet resultSet) throws SQLException {
        List<TaskAnswerStatus> taskList = new ArrayList<>();
        while (resultSet.next()) {
            TaskAnswerStatus task = TaskAnswerStatus.resolveById(resultSet.getInt("id"));
            taskList.add(task);
        }
        return taskList;
    }
}
