package com.motivateme;

/**
 * Created by Sjoerd on 18-5-2016.
 */
public class Profile {

    private String username;
    private String name;
    private int age;
    private int profilePicture;

    public Profile(String username, String name, int age, int profilePicture) {
        this.username = username;
        this.name = name;
        this.age = age;
        this.profilePicture = profilePicture;
    }


    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public int getProfilePicture() {
        return profilePicture;
    }
}
