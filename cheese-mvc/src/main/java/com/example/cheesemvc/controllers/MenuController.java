package com.example.cheesemvc.controllers;

import com.example.cheesemvc.models.Menu;
import com.example.cheesemvc.models.data.CheeseDao;
import com.example.cheesemvc.models.data.MenuDao;
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
@RequestMapping(value = "menu")
public class MenuController {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private CheeseDao cheeseDao;

    @RequestMapping (value = "")
    public String index (Model model) {
        model.addAttribute("title", "Menus");
        model.addAttribute("menus", menuDao.findAll());

        return "menu/index:";
    }

    @RequestMapping (value = "add", method = RequestMethod.GET)
    public String displayAddForm(Model model) {
        model.addAttribute(new Menu());

        return "menu/add:";
    }

    @RequestMapping(value="add", method=RequestMethod.POST)
    public String processAddForm(@ModelAttribute @Valid Menu newMenu, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Menu");
            return "menu/add:";
        }

        menuDao.save(newMenu);
        return "redirect:view/" + menu.getId();
    }



}
