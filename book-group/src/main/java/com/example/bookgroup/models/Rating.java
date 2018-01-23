package com.example.bookgroup.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Rating {

    private int rate;

    @Id
    @GeneratedValue
    private int id;

    @ManyToMany(mappedBy = "ratings")
    private List<Book> books;

    public Rating(int rate) {
        this.rate = rate;
    }

    public Rating() {
    }

    public int getRate() {return rate; }

    public void setRate(int aRate) {this.rate=aRate; }

    public int getId() {return id; }


    public List<Rating> findRatings(int bookId) {
        return null;
    }
    //public void addItem(Rating item) { ratings.add(item); }




}

