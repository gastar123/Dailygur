
package com.example.spidertest.dto;

import java.util.HashMap;
import java.util.Map;

public class Tag {

    private String name;
    private String displayName;
    private Integer followers;
    private Integer totalItems;
    private Boolean following;
    private Boolean isWhitelisted;
    private String backgroundHash;
    private String thumbnailHash;
    private String accent;
    private Boolean backgroundIsAnimated;
    private Boolean thumbnailIsAnimated;
    private Boolean isPromoted;
    private String description;
    private Object logoHash;
    private Object logoDestinationUrl;
//    private DescriptionAnnotations descriptionAnnotations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public Boolean getFollowing() {
        return following;
    }

    public void setFollowing(Boolean following) {
        this.following = following;
    }

    public Boolean getIsWhitelisted() {
        return isWhitelisted;
    }

    public void setIsWhitelisted(Boolean isWhitelisted) {
        this.isWhitelisted = isWhitelisted;
    }

    public String getBackgroundHash() {
        return backgroundHash;
    }

    public void setBackgroundHash(String backgroundHash) {
        this.backgroundHash = backgroundHash;
    }

    public String getThumbnailHash() {
        return thumbnailHash;
    }

    public void setThumbnailHash(String thumbnailHash) {
        this.thumbnailHash = thumbnailHash;
    }

    public String getAccent() {
        return accent;
    }

    public void setAccent(String accent) {
        this.accent = accent;
    }

    public Boolean getBackgroundIsAnimated() {
        return backgroundIsAnimated;
    }

    public void setBackgroundIsAnimated(Boolean backgroundIsAnimated) {
        this.backgroundIsAnimated = backgroundIsAnimated;
    }

    public Boolean getThumbnailIsAnimated() {
        return thumbnailIsAnimated;
    }

    public void setThumbnailIsAnimated(Boolean thumbnailIsAnimated) {
        this.thumbnailIsAnimated = thumbnailIsAnimated;
    }

    public Boolean getIsPromoted() {
        return isPromoted;
    }

    public void setIsPromoted(Boolean isPromoted) {
        this.isPromoted = isPromoted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getLogoHash() {
        return logoHash;
    }

    public void setLogoHash(Object logoHash) {
        this.logoHash = logoHash;
    }

    public Object getLogoDestinationUrl() {
        return logoDestinationUrl;
    }

    public void setLogoDestinationUrl(Object logoDestinationUrl) {
        this.logoDestinationUrl = logoDestinationUrl;
    }

//    public DescriptionAnnotations getDescriptionAnnotations() {
//        return descriptionAnnotations;
//    }
//
//    public void setDescriptionAnnotations(DescriptionAnnotations descriptionAnnotations) {
//        this.descriptionAnnotations = descriptionAnnotations;
//    }
}
