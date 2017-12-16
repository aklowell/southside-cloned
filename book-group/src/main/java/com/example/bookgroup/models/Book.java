package com.example.bookgroup.models;


import java.util.ArrayList;
import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Book {

    private int id;
    private static int nextId = 1;

    @NotNull
    @Size(min = 1, message="Title cannot be empty.")
    private String title;
    private String authorFirstName;
    private String authorLastName;
    private int pages;
    private Date meetingDate;

    public Book(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;


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
        id = nextId;
        nextId++;
    }

    public Book(String title, String authorFirstName, String authorLastName, int pages, Date meetingDate, float averageRating) {
        this();
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.pages = pages;
        this.meetingDate = meetingDate;

        this.Ratings = new ArrayList<>();

        this.averageRating = averageRating;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Book.nextId = nextId;
    }

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

    public void setId(int id) {
        this.id = id;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public ArrayList getRatings() {
        return Ratings;
    }



}
