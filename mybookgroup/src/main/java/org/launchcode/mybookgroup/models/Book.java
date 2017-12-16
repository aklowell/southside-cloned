package org.launchcode.mybookgroup.models;


import java.util.Date;

public class Book {


    private int id;
    private static int nextId = 1;

    @NotNull
    private String title;
    private String authorFirstName;
    private String authorLastName;
    private int pages;
    private Date meetingDate;
    private float averageRating;

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

    public Book(String title, String authorFirstName, String authorLastName, int pages, String genre, Date meetingDate, float averageRating) {
        this();
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.pages = pages;
        this.meetingDate = meetingDate;
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

    public float getAverageRating() {
        return averageRating;
    }


}
