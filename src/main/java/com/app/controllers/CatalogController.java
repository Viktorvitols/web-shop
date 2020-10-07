package com.app.controllers;

import com.app.model.Catalog;
import com.app.services.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @GetMapping("/catalog")
    public String getCatalog(Model model) {
//        model.addAttribute("catalog" , new Catalog());
        String productName = "Svetlana";
        model.addAttribute("price", catalogService.getProductPriceByName(productName));
        model.addAttribute("name", productName);
        return "catalog";
    }
}
