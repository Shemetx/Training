package com.tritpo.training.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *  Util class to get last inserted entity from database
 *
 * @author Yurchyk Uladzislau on 2021-01-15.
 * @version 0.0.1
 */
public class LastInsertDao {
    private static final Logger logger = LogManager.getLogger(LastInsertDao.class);
    public static int getLastDao(Connection connection)  {
        String sql = "SELECT @@IDENTITY";
        int i = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            resultSet.next();
            i = resultSet.getInt("@@IDENTITY");
            preparedStatement.close();
            logger.info("Last insert dao success");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Last insert dao failed");
        }
        return i;
    }
}
