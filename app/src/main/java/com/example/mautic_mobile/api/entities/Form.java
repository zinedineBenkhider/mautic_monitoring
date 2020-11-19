package com.example.mautic_mobile.api.entities;

import java.util.Date;

public class Form {
    private int id;
    private String name;
    private Boolean isPublished;
    private Date publishUp;
    private Date dateAdded;

    public Form(int id, String name, Boolean isPublished, Date publishUp, Date dateAdded) {
        this.id = id;
        this.name = name;
        this.isPublished = isPublished;
        this.publishUp = publishUp;
        this.dateAdded = dateAdded;
    }

    public Date getPublishUp() {
        return publishUp;
    }

    public void setPublishUp(Date publishUp) {
        this.publishUp = publishUp;
    }

    public Boolean getPublished() {
        return isPublished;
    }

    public void setPublished(Boolean published) {
        isPublished = published;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
}
