package com.math.client;

public enum WayPoint {
    LEFT("left"),
    RIGHT("right"),
    CENTER("center"),
    RANDOM("random");

    private String name;
    
    private WayPoint(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
