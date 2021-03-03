package com.tritpo.training.domain;


import com.tritpo.training.util.PropertyReaderUtil;

public enum ApplicationProperties {
    INSTANCE;

    private final String url;
    private final String dbName;
    private final String user;
    private final String password;
    private final Integer availableConnections;
    private final String flyWayPath;

    public static ApplicationProperties getInstance() {
        return INSTANCE;
    }

    ApplicationProperties() {
        this.url = PropertyReaderUtil.url();
        this.dbName = PropertyReaderUtil.dbName();
        this.user = PropertyReaderUtil.user();
        this.password = PropertyReaderUtil.password();
        this.availableConnections = Integer.parseInt(PropertyReaderUtil.availableConnections());
        this.flyWayPath = PropertyReaderUtil.flyWayPath();
    }

    public String getUrl() {
        return url;
    }

    public String getDbName() {
        return dbName;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public Integer getAvailableConnections() {
        return availableConnections;
    }

    public String getFlyWayPath() {
        return flyWayPath;
    }
}
