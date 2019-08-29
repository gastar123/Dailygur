package com.example.spidertest.dto;

public class InnerData {

    private String id;
    private String imageLink;
    private Boolean album;

    public InnerData() {
    }

    public InnerData(String id, String imageLink, Boolean album) {
        this.id = id;
        this.imageLink = imageLink;
        this.album = album;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Boolean getAlbum() {
        return album;
    }

    public void setAlbum(Boolean album) {
        this.album = album;
    }
}
