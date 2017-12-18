package com.example.cheesemvc.controllers;

import com.example.cheesemvc.models.Menu;
import com.example.cheesemvc.models.data.CheeseDao;
import com.example.cheesemvc.models.data.MenuDao;
import com.example.cheesemvc.models.forms.AddMenuItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "menu")
public class MenuController {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private CheeseDao cheeseDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Menus");
        model.addAttribute("menus", menuDao.findAll());

        return "menu/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddForm(Model model) {
        model.addAttribute(new Menu());

        return "menu/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddForm(@ModelAttribute @Valid Menu newMenu, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Menu");
            return "menu/add";
        }

        menuDao.save(newMenu);
        return "redirect:view/" + newMenu.getId();
    }


    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String displayMenus(@PathVariable int id, Model model) {
        Menu menu = menuDao.findOne(id);
        model.addAttribute(menu);

        return "menu/view";
    }

    @RequestMapping(value="add-item/{id}", method=RequestMethod.GET)
    public String addItem(@PathVariable int id, Model model) {
        Menu editMenu = menuDao.findOne(id);

        model.addAttribute("menu", editMenu);
        AddMenuItemForm addMenuItemForm = new AddMenuItemForm();
        model.addAttribute("title", "Add item to menu " + editMenu.getName());
        model.addAttribute("form", addMenuItemForm);
        model.addAttribute("cheeses", cheeseDao.findAll());
   //     model.addAttribute("menuId", addMenuItemForm.getMenuId());

        return "menu/add-item";
    }

    @RequestMapping(value="add-item", method=RequestMethod.POST)
    public String addItem(Model model, @Valid Menu newMenu, int cheeseId, int menuId, Errors errors) {
        if (errors.hasErrors()) {

            return "menu/add-item";
        }

        model.addAttribute(cheeseDao.findOne(cheeseId));
        model.addAttribute(menuDao.findOne(menuId));


        menuDao.save(newMenu);

        return "redirect:menu/index";
    }


}
