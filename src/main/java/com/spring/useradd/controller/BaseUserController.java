package com.spring.useradd.controller;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.useradd.entity.User;

import com.spring.useradd.service.impl.UserAuthentiationServiceImpl;


@Controller
@RequestMapping("/")
public class BaseUserController {

    @Autowired
    UserAuthentiationServiceImpl userAuthenticationService;

    @GetMapping("/")
    public String indexPage(){
        return "home";
    }

    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("userRegisterForm", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user){
        userAuthenticationService.registerUser(user);
        return "redirect:/login";
    }   
}
