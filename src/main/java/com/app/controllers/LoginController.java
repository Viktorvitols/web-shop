package com.app.controllers;

import com.app.model.Login;
import com.app.services.LoginService;
import com.app.session.CurrentUser;
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
    private LoginService loginService;

    @Autowired
    private CurrentUser currentUser;

    @GetMapping("/login")
    public String getLoginForm(Model model) {
        if (currentUser.getId() != null) {
            model.addAttribute("id", currentUser.getId());
            return "login_response";
        }
        model.addAttribute("login", new Login());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute Login login, Model model) throws SQLException {
        Integer userId = loginService.getUserId(login);
        if (userId != null) {
            currentUser.setId(userId);
            model.addAttribute("id", userId);
//            return "login_response";
        }
        return "redirect:/login";
    }
}
