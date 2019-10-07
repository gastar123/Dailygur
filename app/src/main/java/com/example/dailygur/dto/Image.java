
package com.example.dailygur.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Image {

    public static class ImageHolder {
        private Image data;

        public Image getData() {
            return data;
        }

        public void setData(Image data) {
            this.data = data;
        }
    }

    private String id;
    private String title;
    private String description;
    private Integer datetime;
    private String type;
    private Boolean animated;
    private Integer width;
    private Integer height;
    private Integer size;
    private Integer views;
    private Long bandwidth;
    private Object vote;
    private Boolean favorite;
    private Object nsfw;
    private Object section;
    private Object accountUrl;
    private Object accountId;
    private Boolean isAd;
    private Boolean inMostViral;
    private Boolean hasSound;
    private List<Tag> tags = null;
    private Integer adType;
    private String adUrl;
    private String edited;
    private Boolean inGallery;
    private String link;
    private Integer mp4Size;
    private String mp4;
    private String gifv;
    private String hls;
    private Object commentCount;
    private Object favoriteCount;
    private Object ups;
    private Object downs;
    private Object points;
    private Object score;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDatetime() {
        return datetime;
    }

    public void setDatetime(Integer datetime) {
        this.datetime = datetime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getAnimated() {
        return animated;
    }

    public void setAnimated(Boolean animated) {
        this.animated = animated;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Long getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(Long bandwidth) {
        this.bandwidth = bandwidth;
    }

    public Object getVote() {
        return vote;
    }

    public void setVote(Object vote) {
        this.vote = vote;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public Object getNsfw() {
        return nsfw;
    }

    public void setNsfw(Object nsfw) {
        this.nsfw = nsfw;
    }

    public Object getSection() {
        return section;
    }

    public void setSection(Object section) {
        this.section = section;
    }

    public Object getAccountUrl() {
        return accountUrl;
    }

    public void setAccountUrl(Object accountUrl) {
        this.accountUrl = accountUrl;
    }

    public Object getAccountId() {
        return accountId;
    }

    public void setAccountId(Object accountId) {
        this.accountId = accountId;
    }

    public Boolean getIsAd() {
        return isAd;
    }

    public void setIsAd(Boolean isAd) {
        this.isAd = isAd;
    }

    public Boolean getInMostViral() {
        return inMostViral;
    }

    public void setInMostViral(Boolean inMostViral) {
        this.inMostViral = inMostViral;
    }

    public Boolean getHasSound() {
        return hasSound;
    }

    public void setHasSound(Boolean hasSound) {
        this.hasSound = hasSound;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Integer getAdType() {
        return adType;
    }

    public void setAdType(Integer adType) {
        this.adType = adType;
    }

    public String getAdUrl() {
        return adUrl;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }

    public String getEdited() {
        return edited;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    public Boolean getInGallery() {
        return inGallery;
    }

    public void setInGallery(Boolean inGallery) {
        this.inGallery = inGallery;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getMp4Size() {
        return mp4Size;
    }

    public void setMp4Size(Integer mp4Size) {
        this.mp4Size = mp4Size;
    }

    public String getMp4() {
        return mp4;
    }

    public void setMp4(String mp4) {
        this.mp4 = mp4;
    }

    public String getGifv() {
        return gifv;
    }

    public void setGifv(String gifv) {
        this.gifv = gifv;
    }

    public String getHls() {
        return hls;
    }

    public void setHls(String hls) {
        this.hls = hls;
    }

    public Object getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Object commentCount) {
        this.commentCount = commentCount;
    }

    public Object getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(Object favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public Object getUps() {
        return ups;
    }

    public void setUps(Object ups) {
        this.ups = ups;
    }

    public Object getDowns() {
        return downs;
    }

    public void setDowns(Object downs) {
        this.downs = downs;
    }

    public Object getPoints() {
        return points;
    }

    public void setPoints(Object points) {
        this.points = points;
    }

    public Object getScore() {
        return score;
    }

    public void setScore(Object score) {
        this.score = score;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
