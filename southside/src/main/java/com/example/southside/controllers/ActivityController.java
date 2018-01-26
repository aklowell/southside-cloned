package com.example.southside.controllers;


import com.example.southside.models.Activity;
import com.example.southside.models.data.ActivityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("activity")
public class ActivityController {

    @Autowired
    private ActivityDao activityDao;

    //Request path: /index - for teachers & admins only
    //from here they add, edit and delete
    @RequestMapping(value= "")
    public String index(Model model) {
        model.addAttribute("activities", activityDao.findAll());
        model.addAttribute("title", "Activity List");
        return "activity/index";
    }

    @RequestMapping(value="add", method= RequestMethod.GET)
    public String displayAddActivityForm(Model model) {
        model.addAttribute("title", "Add Activity");
        model.addAttribute(new Activity());

        return "activity/add";
    }

    @RequestMapping(value="add", method=RequestMethod.POST)
    public String processAddActivityForm(@ModelAttribute @Valid Activity newActivity, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Activity");
            return "activity/add";
        }

        //TODO Add a way to check if activity has already been entered, confirm user wants to enter it again.
        activityDao.save(newActivity);

        return "redirect:/activity";
    }
}
