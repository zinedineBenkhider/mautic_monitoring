package com.example.mautic_mobile.api.entities;

public class Event {
    private int channelId;
    private String name;
    private String description;
    private String type;
    private String eventType;

    public Event(int channelId, String name, String description, String type, String eventType) {
        this.channelId = channelId;
        this.name = name;
        this.description = description;
        this.type = type;
        this.eventType = eventType;
    }

    @Override
    public String toString() {
        return "Event{" +
                "channelId=" + channelId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", eventType='" + eventType + '\'' +
                '}';
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
