package com.example.mautic_mobile.api.entities;

import java.util.Date;
import java.util.List;

public class Email {
    private Boolean isPublished;
    private Date dateAdded;

    private int id;
    private String name;
    private String subject;
    private int readCount;
    private Form unsubscribeForm;
    private int sentCount;
    private Date publishUp;
    private List<Integer> lists;

    public Email(Boolean isPublished, Date dateAdded, int id, String name, String subject, int readCount, Form unsubscribeForm, int sentCount, Date publishUp, List<Integer> lists) {
        this.isPublished = isPublished;
        this.dateAdded = dateAdded;
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.readCount = readCount;
        this.unsubscribeForm = unsubscribeForm;

        this.sentCount = sentCount;
        this.publishUp = publishUp;
        this.lists = lists;
    }

    public void setPublished(Boolean published) {
        isPublished = published;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


    public Boolean getPublished() {
        return isPublished;
    }

    public Date getDateAdded() {
        return dateAdded;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }


    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public List<Integer> getLists() {
        return lists;
    }

    public void setLists(List<Integer> lists) {
        this.lists = lists;
    }

    @Override
    public String toString() {
        return "Email{" +
                "isPublished=" + isPublished +
                ", dateAdded=" + dateAdded +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", readCount=" + readCount +
                ", lists=" + lists +
                '}';
    }

    public int getSentCount() {
        return sentCount;
    }

    public void setSentCount(int sentCount) {
        this.sentCount = sentCount;
    }

    public Form getUnsubscribeForm() {
        return unsubscribeForm;
    }

    public void setUnsubscribeForm(Form unsubscribeForm) {
        this.unsubscribeForm = unsubscribeForm;
    }

    public Date getPublishUp() {
        return publishUp;
    }

    public void setPublishUp(Date publishUp) {
        this.publishUp = publishUp;
    }
}
