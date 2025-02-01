package com.github.xeniale.backendexamples.frameworks.rx.java;


import ru.vimpelcom.util.Timings;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Map;

/**
 * Created by kshekhovtsova on 22.07.2015.
 */
public class BatchTest {




    private static Timings CONN_TIMING = new Timings("BatchTest test", 1l);
    private static int counter1 = 0;
    private static int counter2 = 0;
    public static void main(String[] args) {
//        String url = "jdbc:oracle:thin:@//192.168.14.91:1521/OTTHUB";
//        String driver = "oracle.jdbc.OracleDriver";
//        String user = "AUTODEPLOYMENT";
//        String password = "AUTODEPLOYMENT11";
//        try {
//            Class.forName(driver);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try (Connection dbConn = ((user != null) && (!user.isEmpty()) && (password != null))
//                ? DriverManager.getConnection(url, user, password) : DriverManager.getConnection(url);) {
//            dbConn.setAutoCommit(false);
//            //getColumns(dbConn);
//            test(dbConn, 10);
//            CONN_TIMING.report();
//            dbConn.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        Nameless nameless = new Nameless();
        nameless.run(1, "yuhooo-oo-oo");
        nameless.run(2, "yeaaa-aa-aa-hh");
        System.out.println(Thread.currentThread().getId());
        System.out.println(Thread.currentThread().getName());
    }

    public static void getColumns(Connection connection) throws SQLException {
        try(PreparedStatement ps = connection.prepareStatement("select CONFIG_ID, CONFIG_NAME, PARENT_ID from CONFIG_TMP")) {
            ResultSetMetaData rsmd = ps.getMetaData();
            int type = rsmd.getColumnType(3);
            System.out.print(type);
        }
    }

    public static void test(Connection connection, int limit) throws SQLException {
        try(PreparedStatement psSel = connection.prepareStatement("select config_tmp_seq.nextval from dual");PreparedStatement ps = connection.prepareStatement("insert into CONFIG_TMP (CONFIG_ID, CONFIG_NAME, PARENT_ID, CONFIG_TYPE) values (config_tmp_seq.nextval, ?, ?, ?)");
            PreparedStatement attrPs = connection.prepareStatement("insert into ATTRIBUTE_TMP (ATTRIBUTE_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE, CONFIG_ID) VALUES (ATTRIBUTE_TMP_SEQ.nextval, ?, ?, ?)")) {
            Long currentId = 0L;
            ResultSet rs = null;
            CONN_TIMING.takeReadings("before");
            while(counter1 <= 45) {
//                rs = psSel.executeQuery();
//                if (rs.next()) {
//                    ps.setBigDecimal(1, rs.getBigDecimal(1));
//                }
                ps.setString(1, "nnmn");
                ps.setString(3, "c");
                ps.setNull(2, Types.NUMERIC);
                ps.executeUpdate();
                counter1++;
             //   ps.addBatch();
          //      if (counter1 % limit == 0) {
   //                 ps.executeBatch();
//                    ResultSet rs = ps.getGeneratedKeys();
//                    if (rs.next()) {
//                        currentId = rs.getLong(1);
//                    }
          //      }
            //    rs.close();
            }
    //        ps.executeBatch();
            CONN_TIMING.takeReadings("after");
//            while(counter2 <= 15) {
//                attrPs.setString(1, "attr"+counter2);
//                attrPs.setString(2, "val"+counter2);
//                attrPs.setBigDecimal(3, new BigDecimal(2));
//                counter2++;
//                attrPs.addBatch();
//                if (counter2 % limit == 0) {
//                    attrPs.executeBatch();
//                }
//            }
//            attrPs.executeBatch();
        }
    }
}
