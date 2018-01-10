package com.example.bookgroup.controllers;

import com.example.bookgroup.models.Book;
import com.example.bookgroup.models.BookGenre;
import com.example.bookgroup.models.data.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping(value = "")
public class BookController {

    @Autowired
    private BookDao bookDao;

    //welcome page


    @RequestMapping(value = "book", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("books", bookDao.findAll());
        model.addAttribute("title", "Book Group Reading List");
        return "book-list";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayBookAddForm(Model model) {
        model.addAttribute("title", "Add Book");
        model.addAttribute(new Book());
        model.addAttribute("bookGenres", BookGenre.values());
        return "add";
    }

    //?MODEL BINDING
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processBookAddForm(Model model, @ModelAttribute @Valid Book newBook, Errors errors) {
        if (errors.hasErrors() || newBook.getTitle().isEmpty()) {
            model.addAttribute("title", "Add Book");
            return "book/book-list";
        }

        bookDao.save(newBook);

        return "redirect:book";
    }

    //rate a book
    @RequestMapping(value = "rate/{id}", method = RequestMethod.GET)
    public String displayRateForm(@PathVariable int id, Model model) {
        Book rateBook = bookDao.findOne(id);

        model.addAttribute("bookTitle", rateBook.getTitle());
        //model.addAttribute("bookAuthorFirst",rateBook.getAuthorFirstName());
        model.addAttribute("bookAuthorLast", rateBook.getAuthorLastName());
        model.addAttribute("bookId", rateBook.getId());

        return "rate-book";
    }

 /*  @RequestMapping(value = "rate/{id}", method = RequestMethod.POST)
    public String processRateForm(int id, String rating) {
        Book rateBook = bookDao.findOne(id);
        BookData.addRating(rating);

        return "redirect:/book";
    }*/
}


