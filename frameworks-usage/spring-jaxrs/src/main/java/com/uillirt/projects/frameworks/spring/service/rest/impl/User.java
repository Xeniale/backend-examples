package com.uillirt.projects.frameworks.spring.service.rest.impl;

import java.util.Date;
import java.util.Map;

public class User {
    private Long id;
    private String login;
    private String password;
    private int active;
    private Date lastLoginDate;
    private Map<String, Date> tokenToExpirationDateMap;

    public User() {
    }

    public User(Long id, String login, String password, int active, Date lastLoginDate, Map<String, Date> tokenToExpirationDateMap) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.active = active;
        this.lastLoginDate = lastLoginDate;
        this.tokenToExpirationDateMap = tokenToExpirationDateMap;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Map<String, Date> getTokenToExpirationDateMap() {
        return tokenToExpirationDateMap;
    }

    public void setTokenToExpirationDateMap(Map<String, Date> tokenToExpirationDateMap) {
        this.tokenToExpirationDateMap = tokenToExpirationDateMap;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
}
