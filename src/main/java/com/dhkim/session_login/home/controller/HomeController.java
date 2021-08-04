package com.dhkim.session_login.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class HomeController {

    @GetMapping("/")
    public ModelAndView homeController()
    {
        return new ModelAndView("/home/home");
    }

}
