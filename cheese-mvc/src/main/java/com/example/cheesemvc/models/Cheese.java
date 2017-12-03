package com.example.cheesemvc.models;

import com.sun.istack.internal.NotNull;


public class Cheese {

    @NotNull
    @Size(min=3, max=15)
    private String cheeseName;

    @NotNull
    @Size(min=1, message="Description must not be empty.")
    private String cheeseType;

    private cheeseVariety variety;

    public cheeseVariety getVariety() {
        return variety;
    }

    public void setVariety(cheeseVariety variety) {
        this.variety = variety;
    }



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
