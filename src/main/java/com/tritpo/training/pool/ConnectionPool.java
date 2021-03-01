package com.tritpo.training.pool;


import com.tritpo.training.domain.ApplicationProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static ConnectionPool connectionPool;
    private BlockingQueue<ConnectionProxy> connectionQueue;



    public static ConnectionPool getInstance() {
        if (connectionPool == null) {
            connectionPool = new ConnectionPool();
        }

        return connectionPool;
    }

    /**
     * Constructor responsible for initialising connection pool
     */
    private ConnectionPool() {
        ApplicationProperties appProp = ApplicationProperties.getInstance();
        connectionQueue = new LinkedBlockingQueue<>(appProp.getAvailableConnections());
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < appProp.getAvailableConnections(); i++) {
            try {
                ConnectionProxy connection = new ConnectionProxy(DriverManager.getConnection(appProp.getUrl() + appProp.getDbName(), appProp.getUser(), appProp.getPassword()));
                connectionQueue.add(connection);
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("Cannot create connection to database");
                return;
            }
        }
        logger.info("Connection pool successfully created");
    }

    public BlockingQueue<ConnectionProxy> getConnectionQueue() {
        return connectionQueue;
    }

    /**
     * method responsible for receiving connection form pool
     */
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = connectionQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }
   //  connectionQueue.poll()
    /**
     * method responsible for returning connection to pool after being executed
     */
    public Connection returnToPool(ConnectionProxy connection) {
        connectionQueue.add(connection);
        return connection;
    }
    /**
     * method responsible for closing all connection from pool
     */
    public void destroyPool() {

        for (ConnectionProxy connectionProxy : connectionQueue) {
            try {
                connectionProxy.realClose();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                logger.error("Cant destroy connection pool");
                return;
            }
        }
        logger.info("Connections successfully destroyed");
        connectionQueue.clear();
    }

}
