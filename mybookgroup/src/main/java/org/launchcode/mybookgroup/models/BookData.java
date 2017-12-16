package org.launchcode.mybookgroup.models;

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
}
