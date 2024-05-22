package com.antonmorozov.spring.coffeeshop.coffeeshop.controllers;

import com.antonmorozov.spring.coffeeshop.coffeeshop.models.Product;
import com.antonmorozov.spring.coffeeshop.coffeeshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Controller
public class ConsumerProductController {

    private final ProductService productService;

    @Autowired
    public ConsumerProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/consumerproducts")
    public String getConsumerProducts(Model model) {
        List<Product> department2Products = productService.getCoffeeMachines(2L);

        List<Product> department7Products = productService.getCoffeeMachines(7L);
        department2Products.addAll(department7Products);

        Set<Product> combinedProducts = new LinkedHashSet<>(department2Products);

        model.addAttribute("consumerproducts", new ArrayList<>(combinedProducts));
        return "consumerproducts";
    }
}
