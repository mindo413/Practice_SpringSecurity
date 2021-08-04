package com.dhkim.session_login.login.controller;

import com.dhkim.session_login.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String initLogin() {
        return "login/login";
    }

}
