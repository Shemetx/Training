package com.tritpo.training.dao.reader.impl;

import com.tritpo.training.dao.reader.AbstractResultSetReader;
import com.tritpo.training.domain.TaskAnswer;
import com.tritpo.training.domain.TaskAnswerStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public enum  TaskAnswerReaderImpl implements AbstractResultSetReader<TaskAnswer> {
    INSTANCE;
    public static TaskAnswerReaderImpl getInstance() {return INSTANCE;}
    @Override
    public TaskAnswer oneObjectRead(ResultSet resultSet) throws SQLException {
        return TaskAnswer.Builder.newInstance()
                .setId(resultSet.getInt("id"))
                .setAnswer(resultSet.getString("answer"))
                .setComment(resultSet.getString("comment"))
                .setStatus(TaskAnswerStatus.resolveById(resultSet.getInt("task_status_id")))
                .setUserId(resultSet.getInt("user_id"))
                .setTaskId(resultSet.getInt("task_id"))
                .build();
    }

    @Override
    public List<TaskAnswer> manyObjectsRead(ResultSet resultSet) throws SQLException  {
        List<TaskAnswer> taskAnswerList = new ArrayList<>();
        while (resultSet.next()) {
            TaskAnswer taskAnswer = TaskAnswer.Builder.newInstance()
                    .setId(resultSet.getInt("id"))
                    .setAnswer(resultSet.getString("answer"))
                    .setComment(resultSet.getString("comment"))
                    .setStatus(TaskAnswerStatus.resolveById(resultSet.getInt("task_status_id")))
                    .setUserId(resultSet.getInt("user_id"))
                    .setTaskId(resultSet.getInt("task_id"))
                    .build();

            taskAnswerList.add(taskAnswer);
        }
       return taskAnswerList;

    }
}
