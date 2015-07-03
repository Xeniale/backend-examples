package com.uillirt.projects.frameworks.rx.java;

import com.github.davidmoten.rx.jdbc.Database;
import rx.Observable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kshekhovtsova on 03.07.2015.
 */
public class RXMain {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@//192.168.14.91:1521/OTTHUB";
        String driver = "oracle.jdbc.OracleDriver";
        String user = "BRIDGES";
        String password = "BRIDGES11";
        String sql = "select first_name from customer where first_name > ?";
        try {
            Class.forName(driver);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        Database db = Database.builder().url(url).username(user).password(password).pool(2,30).build();
        testRx(db, sql, 10);
        try (Connection dbConn = ((user != null) && (!user.isEmpty()) && (password != null))
                ? DriverManager.getConnection(url, user, password) : DriverManager.getConnection(url);) {
            testSimple(dbConn, sql, 10);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Connection createConnection(String dbDriver, String dbUrl, String dbUser, String dbPassword) throws Exception {
        Class.forName(dbDriver);
        Connection dbConn = ((dbUser != null) && (!dbUser.isEmpty()) && (dbPassword != null))
                ? DriverManager.getConnection(dbUrl, dbUser, dbPassword) : DriverManager.getConnection(dbUrl);
        return dbConn;
    }

    static void testRx(Database db, String sql, int n) {
        long t1 = System.nanoTime();
        for (int i = 0; i < n; i++) {
            Observable<String> names = db
                    .select(sql)
                    .parameter("Alex")
                    .getAs(String.class);
            /*
            * List<String> names = db
                    .select(sql)
                    .parameter("Alex")
                    .getAs(String.class)
                    .toList().toBlocking().single();
            * */
        }
        long t2 = System.nanoTime();
        System.out.format("rx jdbc time: %d%n for %d selects\n", (t2-t1)/1000000, n);
    }

    static void testSimple(Connection connection, String sql, int n) {
        try (PreparedStatement ps = connection.prepareStatement(sql);) {
            long t1 = System.nanoTime();
            for (int i = 0; i < n; i++) {
                ps.setObject(1, "ALEX");
                List<String> list = new ArrayList<String>();
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        list.add(rs.getString(1));
                    }
                }
            }
            long t2 = System.nanoTime();
            System.out.format("simple connection time: %d%n for %d selects\n", (t2-t1)/1000000, n);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
