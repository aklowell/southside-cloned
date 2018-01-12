package com.example.bookgroup.controllers;

import com.example.bookgroup.models.Book;
import com.example.bookgroup.models.BookGenre;
import com.example.bookgroup.models.Members;
import com.example.bookgroup.models.data.BookDao;
import com.example.bookgroup.models.data.MembersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping(value = "book")
public class BookController {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private MembersDao membersDao;

    //welcome page


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("books", bookDao.findAll());
        model.addAttribute("title", "Book Group Reading List");
        return "book/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayBookAddForm(Model model) {
        model.addAttribute("title", "Add Book");
        model.addAttribute(new Book());
        model.addAttribute("bookGenres", BookGenre.values());
        model.addAttribute("memberss", membersDao.findAll());
        return "book/add";
    }

    //?MODEL BINDING
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processBookAddForm(@ModelAttribute @Valid Book newBook, Errors errors,
                                     @RequestParam int membersId, Model model) {
        if (errors.hasErrors() || newBook.getTitle().isEmpty()) {
            model.addAttribute("title", "Add Book");
            model.addAttribute("members", membersDao.findAll());
            return "book/add";
        }

        Members mem = membersDao.findOne(membersId);
        newBook.setMembers(mem);
        bookDao.save(newBook);

        return "book/book-list";
    }

    //list each member's books
    @RequestMapping(value="book-list", method=RequestMethod.GET)
    public String members(Model model, @RequestParam int id) {
        Members mem = membersDao.findOne(id);
        //TODO put in a link so each member's books get listed
        //List<Book> book = bookDao.findAll() ??
        model.addAttribute("books", bookDao.findAll());
        model.addAttribute("books", "Member Books: " + mem.getMemberName());
        //same as cheese/index
        return "book/book-list";

    }


    //edit a book's details
    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String displayEditForm(@PathVariable int id, Model model) {
        Book editBook = bookDao.findOne(id);

        model.addAttribute("title", editBook.getTitle());
        model.addAttribute("authorFirstName", editBook.getAuthorFirstName());
        model.addAttribute("authorLastName", editBook.getAuthorLastName());
        model.addAttribute("bookId", bookDao.findOne(id));

        return "book/edit";
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.POST)
    public String processEditForm(int id, String title, String authorFirstName, String authorLastName) {
        Book updateBook = bookDao.findOne(id);
        updateBook.setTitle(title);
        updateBook.setAuthorFirstName(authorFirstName);
        updateBook.setAuthorLastName(authorLastName);

        bookDao.save(updateBook);

        return "redirect:/book";
    }

    //show one book's details
    @RequestMapping(value="detail/{id}", method = RequestMethod.GET)
    public String displayDetailsForm(@PathVariable int id, Model model) {
        Book displayBook = bookDao.findOne(id);
        model.addAttribute("title", displayBook.getTitle());
        model.addAttribute("bookId", bookDao.findOne(id));
        model.addAttribute(displayBook);

        return "book/detail";

    }

    /*TODO list of recommended books
    @RequestMapping(value="recommended", method = RequestMethod.GET)
    public String displayRecommended(Model model) {

        Book books = BookDao.listRecommended(true);
        return "book/recommended";
    }
*/
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


