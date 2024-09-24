package com.spring.useradd.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.useradd.entity.User;
import com.spring.useradd.service.impl.UserAuthentiationServiceImpl;


@Controller
@RequestMapping("/login")
public class UserAuthenticationController {

    @Autowired
    UserAuthentiationServiceImpl userAuthenticationService;

    @GetMapping
    public String logInForm(Model model){
        model.addAttribute("userLoginForm", new User());
        return "login";
    }

    @PostMapping
    public String checkLogInDetails(@ModelAttribute User user, Model model, RedirectAttributes redirectAttributes) {
      
        boolean checkAuthDetails = userAuthenticationService.checkAuthDetails(user);
        
        if(checkAuthDetails){ 
            redirectAttributes.addFlashAttribute("userName", user.getUserName());
            return "redirect:/user/index";
        }
        model.addAttribute("worngCredential", "The provided credentials were worng.");
        model.addAttribute("userLoginForm", new User());
        return "login";  
    }
    
}

