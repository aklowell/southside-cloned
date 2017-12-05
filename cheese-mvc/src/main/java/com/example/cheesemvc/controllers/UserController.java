
package com.example.cheesemvc.controllers;

import com.example.cheesemvc.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;


@Controller
@RequestMapping("user")
public class UserController {


    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {

        model.addAttribute(new User());

        return "user/add";
    }


    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid User user, Errors errors, String verify) {
        if (errors.hasErrors()) {
            model.addAttribute(user);
            return "user/add";
        }

        if (!(user.getPassword().equals(verify)) || ((verify.isEmpty()) || (user.getPassword().isEmpty()))) {
            model.addAttribute("error", "Password and verify must be filled in and match.");


            return "user/add";
    }

        else {
            model.addAttribute("username", user.getUsername());
            return "user/index";
        }

    }

}
