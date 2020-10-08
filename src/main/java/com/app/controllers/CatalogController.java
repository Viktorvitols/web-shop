package com.app.controllers;

import com.app.model.Catalog;
import com.app.services.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @GetMapping("/catalog")
    public String getCatalog(@ModelAttribute Catalog catalog, Model model) {
        model.addAttribute("item" , new Catalog());
        catalogService.getItem();
        return "catalog";

    }
}
