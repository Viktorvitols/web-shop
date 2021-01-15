package com.app.controllers;

import com.app.model.Login;
import com.app.services.LangService;
import com.app.services.LoginService;
import com.app.session.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private CurrentUser currentUser;

    @Autowired
    private LangService langService;

    @GetMapping("/login")
    public String getLoginForm(Model model) {
        if (currentUser.getId() != null) {
            model.addAttribute("id", currentUser.getId());
            model.addAttribute("username", currentUser.getName());
            return "redirect:/catalog";
        }
        model.addAttribute("login", new Login());
        return "login";
    }

    @GetMapping("/translations")
    public String getTranslationsPage(Model model) {
        if (currentUser.getLangId() == null) {
            currentUser.setLangId(1);
        }
        model.addAttribute("lang", langService.getTranslations(currentUser.getLangId(), "homepage"));

        return "translations";
    }

    @GetMapping("/setLang/{langId}")
    @ResponseBody
    public void setLang(@PathVariable(value = "langId") Integer langId) {
        currentUser.setLangId(langId);
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute Login login, Model model) throws SQLException {
        Integer userId = loginService.getUserId(login);
        String username = loginService.getUsername(login);
        if (userId != null) {
            currentUser.setId(userId);
            currentUser.setName(login.getUsername());
            model.addAttribute("id", userId);
            model.addAttribute("username", username);
            return "redirect:/catalog";
        }
        return "redirect:/login";
    }

    @GetMapping("/logout_old")
    public String logoutUser(HttpSession session) {
        if (currentUser.getId() != null) {
            session.invalidate();
        }
        return "redirect:/login";
    }
}
