package com.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecController {

    @GetMapping("/sechome")
    public String getSecHome() {
        return "sechome";
    }

    @GetMapping("/sechello")
    public String getSecHello() {
        return "sechello";
    }

    @GetMapping("/seclogin")
    public String getSecLogin() {
        return "seclogin";
    }

}
