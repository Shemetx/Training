package com.tritpo.training.dao.reader.impl;

import com.tritpo.training.dao.reader.AbstractResultSetReader;
import com.tritpo.training.domain.DayOfWeek;
import com.tritpo.training.domain.Schedule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public enum  ScheduleReaderImpl implements AbstractResultSetReader<Schedule> {
    INSTANCE;

    public static ScheduleReaderImpl getInstance() {return  INSTANCE;}


    @Override
    public Schedule oneObjectRead(ResultSet resultSet) throws SQLException {
        return Schedule.Builder.newInstance()
                .setId(resultSet.getInt("id"))
                .setStartTime(resultSet.getTime("time").toLocalTime())
                .setDayOfWeek(DayOfWeek.resolveById(resultSet.getInt("day_id")))
                .build();
    }

    @Override
    public List<Schedule> manyObjectsRead(ResultSet resultSet) throws SQLException {
        List<Schedule> scheduleList = new ArrayList<>();
        while (resultSet.next()) {
            Schedule schedule = Schedule.Builder.newInstance()
                    .setId(resultSet.getInt("id"))
                    .setStartTime(resultSet.getTime("time").toLocalTime())
                    .setDayOfWeek(DayOfWeek.resolveById(resultSet.getInt("day_id")))
                    .build();
            scheduleList.add(schedule);
        }
        return scheduleList;

    }
}
