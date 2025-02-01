package com.github.xeniale.backendexamples.frameworks.hicariCP;

import com.github.xeniale.backendexamples.frameworks.hicariCP.factory.HikariCPDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by kshekhovtsova on 11.05.2015.
 */
public class HikariCPMain {

    private static DataSource dataSource;

    public static void main(String[] args) {
        final Properties properties = getProperties();
        dataSource = HikariCPDataSourceFactory.createDataSource(properties);
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT ID, DATE, EXT_CODE, TYPE from TEST.TRANSACTION");
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getObject("ID"));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Error occurred while connecting to DB", e);
        }
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
       // properties.setProperty("dataSourceClassName", "org.h2.jdbcx.JdbcDataSource");
        properties.setProperty("driverClassName", "org.h2.Driver");
        properties.setProperty("jdbcUrl", "jdbc:h2:~/test");
        properties.setProperty("username", "sa");
        properties.setProperty("password", "");
        return properties;
    }
}
