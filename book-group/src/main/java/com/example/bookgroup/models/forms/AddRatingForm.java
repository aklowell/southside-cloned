package com.example.bookgroup.models.forms;

import com.example.bookgroup.models.Book;
import com.example.bookgroup.models.Rating;

import javax.validation.constraints.NotNull;

public class AddRatingForm {

    private Book book;

    private Iterable<Rating> ratings;

    public Book getBook() {return book;}

    public void setBook(Book book) { this.book = book;}

    public Iterable<Rating> getRatings() { return ratings;}

    public void setRatings(Iterable<Rating> ratings) { this.ratings= ratings;}

    @NotNull
    private int bookId;

    @NotNull
    private int ratingId;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public AddRatingForm(Book book, Iterable<Rating> ratings) {
        this.book=book;
        this.ratings=ratings;
    }

    public AddRatingForm() {}


}
