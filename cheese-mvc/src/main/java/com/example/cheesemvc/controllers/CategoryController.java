package com.example.cheesemvc.controllers;


import com.example.cheesemvc.models.Category;
import com.example.cheesemvc.models.data.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "category")
public class CategoryController {

    @Autowired
    private CategoryDao categoryDao;


    @RequestMapping(value = "", method=RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("title", "Categories");
        model.addAttribute("categories", categoryDao.findAll());
        return "category/index";

        }

    @RequestMapping(value = "add",  method = RequestMethod.GET)
    public String displayCatAddForm (Model model) {
        model.addAttribute(new Category());
        model.addAttribute("title", "Add Category");
        return "category/add";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String processCatAddForm(Model model, @ModelAttribute @Valid Category category, Errors errors) {
        //validate
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Category");
            return "category/add";
        }
        categoryDao.save(category);

        return "redirect:/category";
    }

    }

