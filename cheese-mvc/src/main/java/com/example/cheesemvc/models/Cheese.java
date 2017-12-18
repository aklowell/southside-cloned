package com.example.cheesemvc.models;



import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Cheese {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String cheeseName;

    @NotNull
    @Size(min=1, message="Description must not be empty.")
    private String cheeseType;

    public Category getCategory() {
        return category;
    }

    @ManyToOne
    private Category category;

    @ManyToMany(mappedBy = "cheeses")
    private List<Menu> menus;

    //manytoone goes here - replaces cheesevariety

    public Cheese(String cheeseName, String cheeseType) {
        this.cheeseName = cheeseName;
        this.cheeseType = cheeseType;
    }

    public Cheese() { }

    public int getId() {
        return id;
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

    public void setCategory(Category category) {
        this.category = category;
    }
}
