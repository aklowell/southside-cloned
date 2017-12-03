package com.example.cheesemvc.models;

public enum cheeseVariety {

    HARD {"Hard"},
    SOFT ("Soft"),
    FAKE ("Fake");

    private final String name;

    cheeseVariety(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
