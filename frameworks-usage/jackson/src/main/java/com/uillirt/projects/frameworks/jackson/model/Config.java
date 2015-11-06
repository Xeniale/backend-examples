package com.uillirt.projects.frameworks.jackson.model;

/**
 * Created by kshekhovtsova on 19.05.2015.
 */
public class Config {
    private long id;
    private String name;
    private Config config;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }
}
