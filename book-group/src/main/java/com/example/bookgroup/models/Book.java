package com.example.bookgroup.models;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
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
    private int recommended;
    private double rate;

    private double averageRating;

    @ElementCollection
    private List<Double> ratings;

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public List<Double> getRatings() {
        return ratings;
    }

    public void setRatings(List<Double> ratings) {
        this.ratings = ratings;
    }

    private ArrayList<Book> books = new ArrayList<>();

    //private List<Double> ratings = new ArrayList<>();



     public double calculateAverage(List bookRatings) {
        //List<Double> ratings = new ArrayList<>();
        if(ratings==null ||ratings.isEmpty()) {
            return 0;
        }
        double sum = 0;

        ratings.add(rate);

        //List.stream(ratings).average();

        for(Double rating : ratings) {

            sum += rating;
            
        }
        return sum / ratings.size();
       // return averageRating = (sum/ratings.size());
    }




    private static Book instance;



    @ManyToOne
    private Members members;

    public int getRecommended() {
        return recommended;
    }

    //TODO take out if not working
    public static Book getInstance() {
        if (instance == null) {
            instance = new Book();
        }
        return instance;
    }
        //TODO end
    public void setRecommended(int recommended) {
        this.recommended = recommended;
    }



    private BookGenre genre;

    public BookGenre getGenre() {
        return genre;
    }

    public void setGenre(BookGenre genre) {
        this.genre = genre;
    }

    public Book() {
    }

    public Book(String title, String authorFirstName, String authorLastName, int pages, Date meetingDate, List ratings) {
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.pages = pages;
        this.meetingDate = meetingDate;

        this.ratings = ratings;

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






    public Members getMembers() {
        return members;
    }

    public void setMembers(Members members) {
        this.members = members;
    }

    public ArrayList<Book> findRecs(int status) {
        ArrayList<Book> recBooks = new ArrayList<>();
        for (Book book : recBooks) {
            if (book.getRecommended() == status) {
                recBooks.add(book);
            }

        }
        return recBooks;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating() {
        this.averageRating = averageRating;
    }



}