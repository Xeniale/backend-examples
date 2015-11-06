package com.uillirt.projects.frameworks.jackson;

/**
 * Created by kshekhovtsova on 17.08.2015.
 */
public enum ConfigType {
    A("Aggregator"),
    D("Datastore"),
    E("Encapsulator");

    private String name;

    ConfigType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ConfigType fromName(String value) {
        for (ConfigType type : values() ){
            if (type.name.equals(value)) return type;
        }
        return null;
    }
}
