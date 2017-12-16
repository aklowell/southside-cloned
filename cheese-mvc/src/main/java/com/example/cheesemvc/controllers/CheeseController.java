package com.example.cheesemvc.controllers;




import com.example.cheesemvc.models.Category;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.cheesemvc.models.Cheese;
import com.example.cheesemvc.models.data.CategoryDao;
import com.example.cheesemvc.models.data.CheeseDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("cheese")
public class CheeseController {

    @Autowired
    private CheeseDao cheeseDao;

    @Autowired
    private CategoryDao categoryDao;


    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", cheeseDao.findAll());

        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese());
        model.addAttribute("categories", categoryDao.findAll());

        return "cheese/add";
    }

    //MODEL BINDING
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute @Valid Cheese newCheese, Errors errors, @RequestParam int categoryId, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            return "cheese/add";
        }
        Category cat = categoryDao.findOne(categoryId);
        newCheese.setCategory(cat);
        cheeseDao.save(newCheese);
        //Redirect to /cheese

        return "redirect:/cheese";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("title", "Remove Cheese");
        model.addAttribute("cheeses", cheeseDao.findAll());
        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {

        for (int cheeseId : cheeseIds) {
            cheeseDao.delete(cheeseId);

        }
        return "redirect:";
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String displayEditForm(@PathVariable int id, Model model) {
        Cheese editCheese = cheeseDao.findOne(id);

        model.addAttribute("cheeseName", editCheese.getCheeseName());
        model.addAttribute("cheeseType", editCheese.getCheeseType());
        model.addAttribute("cheeseId", cheeseDao.findOne(id));

        return "cheese/edit";
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.POST)
    public String processEditForm(int id, String cheeseName, String cheeseType) {
        Cheese updateCheese = cheeseDao.findOne(id);
        updateCheese.setCheeseName(cheeseName);
        updateCheese.setCheeseType(cheeseType);

        cheeseDao.save(updateCheese);

        return "redirect:/cheese";
    }
}



