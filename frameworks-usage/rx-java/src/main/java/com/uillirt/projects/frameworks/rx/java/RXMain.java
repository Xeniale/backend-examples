package com.uillirt.projects.frameworks.rx.java;

import com.github.davidmoten.rx.jdbc.Database;
import ru.vimpelcom.util.Timings;
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
    private static Timings CONN_TIMING = new Timings("connection test", 1l);
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@//192.168.14.91:1521/OTTHUB";
        String driver = "oracle.jdbc.OracleDriver";
        String user = "BRIDGES";
        String password = "BRIDGES11";
        String sql = "select * from (select q.*, rownum as r from (select  ID \"Id\", STATUS \"Status\", FIRST_NAME \"FirstName\", LAST_NAME \"LastName\", DESCRIPTION \"Descr\", PHONE \"Phone\", 'I' as \"OP\" from BRIDGES.CUSTOMER ) q where rownum <= ? order by\n" +
                " 1 ) where r >= ?";
        try {
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Database db = Database.builder().url(url).username(user).password(password).pool(2, 30).build();
        //testRx(db, sql, 10);
        try (Connection dbConn = ((user != null) && (!user.isEmpty()) && (password != null))
                ? DriverManager.getConnection(url, user, password) : DriverManager.getConnection(url);) {
            testSimple(dbConn, sql, 5000);
        } catch (Exception e) {
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
        Observable<List<String>> observable = null;
        for (int i = 0; i < n; i++) {
            if (observable == null) {
                observable = db.select(sql)
                        .parameter("Alex")
                        .getAs(String.class)
                        .toList();
            }
            else {
                observable.mergeWith(db.select(sql)
                        .parameter("Alex")
                        .getAs(String.class)
                        .toList());
            }
        }
        long t3 = System.nanoTime();
        observable.toBlocking();
        long t4 = System.nanoTime();
        long t2 = System.nanoTime();
        System.out.format("time: %d; to blocking time: %d", (t2 - t1) / 1000000, (t4 - t3) / 1000000);
    }

    static void testSimple(Connection connection, String sql, int size) throws Exception {
        ResultSet rs = null;
        int start = 1, end = size, cnt = 0;
        try (PreparedStatement ps = connection.prepareStatement(sql);) {
            while(true) {
                cnt++;
                ps.setInt(1, end);
                ps.setInt(2, start);
                ps.setFetchSize(size);
                CONN_TIMING.takeReadings("from start to set params? cnt: " + cnt);
                rs = ps.executeQuery();
                CONN_TIMING.takeReadings("executeQuery");
                if (!rs.next()) {
                    if (rs != null && !rs.isClosed()) {
                        rs.close();
                    }
                    break;
                }
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
                start += size;
                end += size;
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }
        finally {
            CONN_TIMING.report();

        }

    }
}
