package com.motivateme;

import java.io.Serializable;

public class Goal implements Serializable {

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
