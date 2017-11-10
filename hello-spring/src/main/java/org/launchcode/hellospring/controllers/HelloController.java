package org.launchcode.hellospring.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class HelloController {

    @RequestMapping(value = "")
    @ResponseBody
    public String index(HttpServletRequest request) {
//Get name out of the parameter localhost:8080/?name=chris
        String name = request.getParameter("name");

        if (name == null) {
            name = "World";

        }

        return "Hello " + name;
    }

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    @ResponseBody
    public String helloForm() {
        String html = "<form method='post'>" +
                "<input type='text' name='name' />" +
                //new form starts here
                "<select name = 'lang'>" +
                "<option value= 'french'>French</option>" +
                "<option value ='spanish'>Spanish</option>" +
                "<option value ='german'>German</option>" +
                "<option value = 'swahili'>Swahili</option>" +
                "<option value = 'navajo'>Navajo</option>" +
                "</select>" +
                "<input type='submit' value='Greet Me!'/>" +
                "</form>";

        return html;
    }

    @RequestMapping(value = "hello", method = RequestMethod.POST)
    @ResponseBody
    public String helloPost(HttpServletRequest request) {
        String language = request.getParameter("lang");
        String person = request.getParameter("name");
        if (language.equals("french")) {
            return "Bonjour " + person;
        } else if (language.equals("spanish")) {
            return "Hola " + person;
        } else if (language.equals("german")) {
            return "Hallo " + person;
        } else if (language.equals("swahili")) {
            return "Hujambo " + person;
        } else if (language.equals("navajo")) {
            return "Yá'át'ééh " + person;
        } else {
            return "Hello ";
        }
    }

    @RequestMapping(value = "hello/{name}")
    @ResponseBody
    public String HelloUrlSegment(@PathVariable String name) {
        return "Hello " + name;
    }

    //@RequestMapping(value = "goodbye")
//
  //      return "redirect:/";
    //}
}
