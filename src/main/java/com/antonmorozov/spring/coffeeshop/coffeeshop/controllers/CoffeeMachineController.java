package com.antonmorozov.spring.coffeeshop.coffeeshop.controllers;

import com.antonmorozov.spring.coffeeshop.coffeeshop.models.Product;
import com.antonmorozov.spring.coffeeshop.coffeeshop.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CoffeeMachineController {

    private final ProductService productService;

    public CoffeeMachineController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/coffeemachines")
    public String getCoffeeMachines(Model model) {
        List<Product> coffeeMachines = productService.getCoffeeMachines(1L);
        model.addAttribute("coffeemachines", coffeeMachines);
        return "coffeemachines";
    }

}
