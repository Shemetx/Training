package com.tritpo.training.listener;

import com.tritpo.training.context.impl.TrainingContext;
import com.tritpo.training.pool.ConnectionPool;
import com.tritpo.training.util.FlyWayMigrationUtil;
import com.tritpo.training.util.PropertyReaderUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;


@WebListener
public class ConnectionPoolListener implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger(ConnectionPoolListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("Initialising cache");
        try {
            PropertyReaderUtil.loadProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FlyWayMigrationUtil.migrateDataBase();
        TrainingContext.getInstance();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Destroy connection pool");
        ConnectionPool.getInstance().destroyPool();
    }
}
