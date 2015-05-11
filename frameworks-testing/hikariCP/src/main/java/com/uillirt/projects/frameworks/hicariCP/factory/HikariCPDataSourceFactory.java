package com.uillirt.projects.frameworks.hicariCP.factory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by kshekhovtsova on 11.05.2015.
 */
public class HikariCPDataSourceFactory {

    public static final String DRIVER_PROPERTY = "driverClassName";

    public static DataSource createDataSource(Properties properties) {
        try {
            Class.forName(properties.getProperty(DRIVER_PROPERTY));
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException("DB driver class not found", e);
        }
        return new HikariDataSource(new HikariConfig(properties));
    }
}
