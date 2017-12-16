package com.example.bookgroup.models;

import java.util.ArrayList;

public class BookData {
    static ArrayList<Book> books = new ArrayList<>();
    //getAll
    public static ArrayList<Book> getAll() {
        return books;
    }

    //add
    public static void add(Book newBook) {
        books.add(newBook);

    }

    //getById
    public static Book getById(int id) {
        Book theBook = null;

        for (Book rateThisBook : books) {
            if (rateThisBook.getId()==id) {

                theBook = rateThisBook;
            }
        }
        return theBook;
    }

    //add rating to the book's array list
  //TODO how??
}
