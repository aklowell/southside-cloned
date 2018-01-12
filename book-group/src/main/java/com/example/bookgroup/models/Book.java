package com.example.bookgroup.models;


import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
public class Book {

    @Id
    @GeneratedValue
    private int id;
    // private static int nextId = 1;

    @NotNull
    @Size(min = 1, message = "Title cannot be empty.")
    private String title;
    private String authorFirstName;
    private String authorLastName;
    private int pages;
    private Date meetingDate;
    private Boolean recommended;

    @ManyToOne
    private Members members;

 /*   public Book(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type; */

    public Boolean getRecommended() {
        return recommended;
    }

    public void setRecommended(Boolean recommended) {
        this.recommended = recommended;
    }

    public Book(ArrayList ratings) {
        Ratings = ratings;
    }

    private ArrayList Ratings;
    private double averageRating;

    private BookGenre genre;

    public BookGenre getGenre() {
        return genre;
    }

    public void setGenre(BookGenre genre) {
        this.genre = genre;
    }

    public Book() {
    }

    public Book(String title, String authorFirstName, String authorLastName, int pages, Date meetingDate, double averageRating) {
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.pages = pages;
        this.meetingDate = meetingDate;

        this.Ratings = new ArrayList<>();

        this.averageRating = averageRating;
    }

  /* public boolean listRecommended() {
        return getRecommended(true);
   }

    private Boolean getRecommended(boolean b) {
    }  */



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Date getMeetingDate() {
    return meetingDate;
    }

    public void setMeetingDate(Date meetingDate) {
        this.meetingDate = meetingDate;
    }

    public int getId() {
        return id;
    }


    public double getAverageRating() {
        return averageRating;
    }

    public ArrayList getRatings() {
        return Ratings;
    }

    public Members getMembers() {
        return members;
    }

    public void setMembers(Members members) {
        this.members = members;
    }





}
