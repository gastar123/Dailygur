package com.example.spidertest.dto;

public class InnerData {

    private String id;
    private String imageLink;
    private Boolean album;
    private Integer coverWidth;
    private Integer coverHeight;

    public InnerData() {
    }

    public InnerData(String id, String imageLink, Boolean album, Integer coverWidth, Integer coverHeight) {
        this.id = id;
        this.imageLink = imageLink;
        this.album = album;
        this.coverWidth = coverWidth;
        this.coverHeight = coverHeight;
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

    public Integer getCoverWidth() {
        return coverWidth;
    }

    public void setCoverWidth(Integer coverWidth) {
        this.coverWidth = coverWidth;
    }

    public Integer getCoverHeight() {
        return coverHeight;
    }

    public void setCoverHeight(Integer coverHeight) {
        this.coverHeight = coverHeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InnerData innerData = (InnerData) o;

        return id.equals(innerData.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
