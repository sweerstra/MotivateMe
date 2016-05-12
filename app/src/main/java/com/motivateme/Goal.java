package com.motivateme;

public class Goal {

    private String name;
    private int points;

    public Goal(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }
}
