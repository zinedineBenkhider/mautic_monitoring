package com.example.mautic_mobile.api.entities;

import java.util.Date;
import java.util.Map;

public class Contact {
    private int id;
    private int points;
    private String color;
    private Date dateAdded;
    private Fields fields;
    private Date lastActive;
    private Date firstActive;
    public Contact(int id, int points, String color, Date dateAdded, Fields fields, Date lastActive, Date firstActive) {
        this.id = id;
        this.points = points;
        this.color = color;
        this.dateAdded = dateAdded;
        this.fields = fields;
        this.lastActive = lastActive;
        this.firstActive = firstActive;
    }
    public String getFirstName(){
        return this.fields.getCore().get("firstname").getValue();
    }
    public String getLastName(){
        return this.fields.getCore().get("lastname").getValue();
    }
    public String getCountry() {
        return this.fields.getCore().get("country").getValue();
    }
    public String getMobileNumber() {
        return this.fields.getCore().get("mobile").getValue();
    }
    public String getEmail() {
        return this.fields.getCore().get("email").getValue();
    }
    public String getCompany(){
        return this.fields.getCore().get("company").getValue();
    }
    public Fields getFields() {
        return this.fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Date getLastActive() {
        return lastActive;
    }

    public void setLastActive(Date lastActive) {
        this.lastActive = lastActive;
    }

    public Date getFirstActive() {
        return firstActive;
    }

    public void setFirstActive(Date firstActive) {
        this.firstActive = firstActive;
    }


    public class Fields{

        private Map<String,Core> core;

        Fields( Map<String, Core> core) {
            this.core=core;
        }

        public Map<String, Core> getCore() {
            return core;
        }

        public void setCore(Map<String, Core> core) {
            this.core = core;
        }
    }
    public class Core{
        private int id;
        private String value;
        Core(int id, String value) {
            this.id = id;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
