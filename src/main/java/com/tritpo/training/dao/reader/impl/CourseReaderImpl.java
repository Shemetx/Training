package com.tritpo.training.dao.reader.impl;

import com.tritpo.training.dao.reader.AbstractResultSetReader;
import com.tritpo.training.domain.Course;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public enum CourseReaderImpl implements AbstractResultSetReader<Course> {

    INSTANCE;

    public static CourseReaderImpl getInstance() { return INSTANCE; }

    @Override
    public Course oneObjectRead(ResultSet resultSet) throws SQLException {

        return Course.Builder.newInstance()
                .setId(resultSet.getInt("id"))
                .setTitle(resultSet.getString("title"))
                .setDescription(resultSet.getString("description"))
                .setDate(resultSet.getDate("start_date").toLocalDate())
                .build();
    }

    @Override
    public List<Course> manyObjectsRead(ResultSet resultSet) throws SQLException {
        List<Course> courses = new ArrayList<>();
        while (resultSet.next()) {
            Course course = Course.Builder.newInstance()
                    .setId(resultSet.getInt("id"))
                    .setTitle(resultSet.getString("title"))
                    .setDescription(resultSet.getString("description"))
                    .setDate(resultSet.getDate("start_date").toLocalDate())
                    .build();
            courses.add(course);
        }
        return courses;
    }
}
