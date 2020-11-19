package com.example.mautic_mobile.api.entities;

import java.util.Date;
import java.util.List;

public class Campaign {
    private boolean isPublished;
    private Date dateAdded;
    private String createdByUser;
    private int id;
    private String name;
    private Date publishUp;
    private List<Event> events;

    public Campaign(boolean isPublished, Date dateAdded, String createdByUser, int id, String name, Date publishUp, List<Event> events) {
        this.isPublished = isPublished;
        this.dateAdded = dateAdded;
        this.createdByUser = createdByUser;
        this.id = id;
        this.name = name;
        this.publishUp = publishUp;
        this.events = events;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(String createdByUser) {
        this.createdByUser = createdByUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPublishUp() {
        return publishUp;
    }

    public void setPublishUp(Date publishUp) {
        this.publishUp = publishUp;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }


    @Override
    public String toString() {
        return "Campaign{" +
                "isPublished=" + isPublished +
                ", dateAdded=" + dateAdded +
                ", createdByUser='" + createdByUser + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", publishUp=" + publishUp +
                ", events=" + events.toString() +
                '}';
    }
}
