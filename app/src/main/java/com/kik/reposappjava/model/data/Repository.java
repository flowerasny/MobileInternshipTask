package com.kik.reposappjava.model.data;

import com.google.gson.annotations.SerializedName;

public class Repository {
    private String name;
    private String description;
    private String language;
    private int watchers;
    @SerializedName("stargazers_count")
    private int stars;
    private int forks;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public int getWatchers() {
        return watchers;
    }

    public int getStars() {
        return stars;
    }

    public int getForks() {
        return forks;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
