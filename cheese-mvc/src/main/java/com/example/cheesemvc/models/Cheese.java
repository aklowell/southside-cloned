package com.example.cheesemvc.models;

public class Cheese {

    private String cheeseName;
    private String cheeseType;
    private int cheeseId;
    private static int nextId = 1;

    public Cheese(String cheeseName, String cheeseType) {
        this();
        this.cheeseName = cheeseName;
        this.cheeseType = cheeseType;
    }

    public int getCheeseId() {
        return cheeseId;
    }

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }

    public Cheese() {
        cheeseId = nextId;
        nextId++;
    }

    public String getCheeseName() {
        return cheeseName;
    }

    public void setCheeseName(String aCheeseName) {
        this.cheeseName = aCheeseName;
    }

    public String getCheeseType() {
        return cheeseType;
    }

    public void setCheeseType(String aCheeseType) {
        this.cheeseType = aCheeseType;
    }
}
