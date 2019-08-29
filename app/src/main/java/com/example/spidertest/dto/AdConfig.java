
package com.example.spidertest.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdConfig {

    private List<String> safeFlags = null;
    private List<Object> highRiskFlags = null;
    private List<Object> unsafeFlags = null;
    private Boolean showsAds;

    public List<String> getSafeFlags() {
        return safeFlags;
    }

    public void setSafeFlags(List<String> safeFlags) {
        this.safeFlags = safeFlags;
    }

    public List<Object> getHighRiskFlags() {
        return highRiskFlags;
    }

    public void setHighRiskFlags(List<Object> highRiskFlags) {
        this.highRiskFlags = highRiskFlags;
    }

    public List<Object> getUnsafeFlags() {
        return unsafeFlags;
    }

    public void setUnsafeFlags(List<Object> unsafeFlags) {
        this.unsafeFlags = unsafeFlags;
    }

    public Boolean getShowsAds() {
        return showsAds;
    }

    public void setShowsAds(Boolean showsAds) {
        this.showsAds = showsAds;
    }
}
