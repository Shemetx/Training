package com.tritpo.training.dao.reader.impl;

import com.tritpo.training.dao.reader.AbstractResultSetReader;
import com.tritpo.training.domain.ManyToManyEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public enum  ManyToManyReaderImpl implements AbstractResultSetReader<ManyToManyEntity> {
    INSTANCE;

    public static ManyToManyReaderImpl getInstance() {return INSTANCE;}
    @Override
    public ManyToManyEntity oneObjectRead(ResultSet resultSet) throws SQLException {
         return ManyToManyEntity.Builder.newInstance()
                 .setFirstId(resultSet.getInt("schedule_id"))
                 .setSecondId(resultSet.getInt("course_id"))
                 .build();
    }

    @Override
    public List<ManyToManyEntity> manyObjectsRead(ResultSet resultSet) throws SQLException {
      List<ManyToManyEntity> list = new ArrayList<>();
      while(resultSet.next()) {
        ManyToManyEntity entity =   ManyToManyEntity.Builder.newInstance()
                  .setFirstId(resultSet.getInt("schedule_id"))
                  .setSecondId(resultSet.getInt("course_id"))
                  .build();
          list.add(entity);
      }
        return list;
    }
}
