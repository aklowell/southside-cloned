package com.example.cheesemvc.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 3, max = 15)
    private String name;

 /*   public ArrayList<Cheese> getCheese() {
        return cheese;
    }

    public void setCheese(ArrayList<Cheese> cheese) {
        this.cheese = cheese;
    }
*/
    @OneToMany
    @JoinColumn(name = "category_id")
    //it knows that you are using this id in category
    private List<Cheese> cheese = new ArrayList<>();

        public Category() {
        }

        public Category(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }



