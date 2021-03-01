package com.tritpo.training.util;


import com.tritpo.training.domain.ApplicationProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;

public class FlyWayMigrationUtil {

    private static final Logger logger = LogManager.getLogger(FlyWayMigrationUtil.class);

    public static void migrateDataBase() {
        ApplicationProperties properties = ApplicationProperties.getInstance();
        Flyway flyway =  Flyway.configure()
                .dataSource(properties.getUrl() + properties.getDbName(),properties.getUser(),properties.getPassword())
                .locations(properties.getFlyWayPath())
                .load();
        flyway.migrate();
        logger.info("Flyway migration ended");
    }
}
