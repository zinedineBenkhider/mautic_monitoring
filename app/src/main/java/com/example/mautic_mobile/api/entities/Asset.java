package com.example.mautic_mobile.api.entities;

import java.util.Date;

public class Asset {
    private int id;
    private String title;
    private boolean isPublished;
    private Date dateAdded;
    private Date publishUp;
    private int downloadCount;
    private int uniqueDownloadCount;
    private int revision;
    private String extension;
    private String downloadUrl;

    public Asset(int id, String title, boolean isPublished, Date dateAdded, Date publishUp, int downloadCount, int uniqueDownloadCount, int revision, String extension, String downloadUrl) {
        this.id = id;
        this.title = title;
        this.isPublished = isPublished;
        this.dateAdded = dateAdded;
        this.publishUp = publishUp;
        this.downloadCount = downloadCount;
        this.uniqueDownloadCount = uniqueDownloadCount;
        this.revision = revision;
        this.extension = extension;
        this.downloadUrl = downloadUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public Date getPublishUp() {
        return publishUp;
    }

    public void setPublishUp(Date publishUp) {
        this.publishUp = publishUp;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public int getUniqueDownloadCount() {
        return uniqueDownloadCount;
    }

    public void setUniqueDownloadCount(int uniqueDownloadCount) {
        this.uniqueDownloadCount = uniqueDownloadCount;
    }

    public int getRevision() {
        return revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }


    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
}
