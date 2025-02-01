package com.github.xeniale.backendexamples.frameworks.spring.model;

/**
 * Created by kshekhovtsova on 27.05.2015.
 */
public class Colleague {
    private String firstName;
    private String lastName;
    private String post;
    private Long id;
    private Work work;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }
}
