package com.motivateme;

import java.io.Serializable;

public class FeedItem implements Serializable {

    private String title;
    private int picture;
    private String description;
    private Goal workingGoal;
    private Profile profile;

    public FeedItem(String title, int picture, String description, Goal workingGoal, Profile profile) {

        this.title = title;
        this.picture = picture;
        this.description = description;
        this.workingGoal = workingGoal;
        this.profile = profile;
    }

    public String getTitle() {
        return title;
    }

    public int getPicture() {
        return picture;
    }

    public String getDescription() {
        return description;
    }

    public Goal getWorkingGoal() {
        return workingGoal;
    }

    public Profile getProfile() {
        return profile;
    }
}
