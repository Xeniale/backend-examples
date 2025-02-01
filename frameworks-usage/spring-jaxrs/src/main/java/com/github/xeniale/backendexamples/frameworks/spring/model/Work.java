package com.github.xeniale.backendexamples.frameworks.spring.model;

/**
 * Created by kshekhovtsova on 27.05.2015.
 */
public class Work {
    private String type;
    private Long id;
    private String description;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
