package com.example.bookgroup.models;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    private ArrayList<Book> books = new ArrayList<>();

    @ManyToOne
    private Members members;


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


    /*find all books that are recommended


    public ArrayList<Book> findRecommended(Boolean recommended) {
        ArrayList<Book> recommendedBooks = new ArrayList<>();
        for (Book book : books) {
            if (recommended.booleanValue())
                recommendedBooks.add(book);

            return recommendedBooks;
        }
        return null;
    }




    public Boolean listRecommended(String b) {
        return recommended;
   }

*/



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
