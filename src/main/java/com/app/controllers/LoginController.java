package com.app.controllers;

import com.app.model.Login;
import com.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String getLoginForm(Model model) {
        model.addAttribute("loginObject", new Login());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute Login login, Model model) throws SQLException {
        model.addAttribute("loginData", login);
        
        return "login_response";
    }
}
