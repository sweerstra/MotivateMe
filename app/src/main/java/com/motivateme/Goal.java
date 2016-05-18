package com.motivateme;

import java.io.Serializable;

public class Goal implements Serializable {

    private String name;
    private int points;
    private int current;
    private int target;

    public Goal(String name, int points, int current, int target) {
        this.name = name;
        this.points = points;
        this.current = current;
        this.target = target;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }
}
