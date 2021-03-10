package com.tritpo.training.dao.reader.impl;

import com.tritpo.training.dao.reader.AbstractResultSetReader;
import com.tritpo.training.domain.DayOfWeek;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public enum DayOfWeekReaderImpl implements AbstractResultSetReader<DayOfWeek> {
    INSTANCE;

    public static DayOfWeekReaderImpl getInstance() { return INSTANCE; }

    @Override
    public DayOfWeek oneObjectRead(ResultSet resultSet) throws SQLException {
        return DayOfWeek.resolveById(resultSet.getInt("id"));
    }

    @Override
    public List<DayOfWeek> manyObjectsRead(ResultSet resultSet) throws SQLException {
        List<DayOfWeek> dayOfWeeks = new ArrayList<>();
        while (resultSet.next()) {
            DayOfWeek dayOfWeek = DayOfWeek.resolveById(resultSet.getInt("id"));
            dayOfWeeks.add(dayOfWeek);
        }
        return dayOfWeeks;
    }
}
