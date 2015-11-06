package com.uillirt.projects.testing.nio;

/**
 * Created by kshekhovtsova on 19.06.2015.
 */
public class TestClass {

    private String url;
    private String driver;
    private long timeout;
    private String user;
    private String pwd;

    public void test() {
      //  setDatabaseParams();
      //  setDatabaseParams2();
    }

    private void setDatabaseParams(String connectionUrl, String driverName, long timeout, String userName, String password) {
        this.url = connectionUrl;
        this.driver = driverName;
        this.timeout = timeout;
        this.user = userName;
        this.pwd = password;
    }

    private void setDatabaseParams2(String param1, String param2, long param3, String param4, String param5) {
        this.url = param1;
        this.driver = param2;
        this.timeout = param3;
        this.user = param4;
        this.pwd = param5;
    }
}
