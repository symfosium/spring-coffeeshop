package com.antonmorozov.spring.coffeeshop.coffeeshop.controllers;

import com.antonmorozov.spring.coffeeshop.coffeeshop.models.Manufacturer;
import com.antonmorozov.spring.coffeeshop.coffeeshop.services.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    @Autowired
    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping("/manufacturers")
    public String manufacturers(Model model) {
        model.addAttribute("manufacturers", manufacturerService.findAllManufacturers());
        return "manufacturers";
    }

    @PostMapping("/add-manufacturer")
    public String addManufacturer(@ModelAttribute Manufacturer manufacturer) {
        manufacturerService.saveManufacturer(manufacturer);
        return "redirect:/manufacturers";
    }

    @GetMapping("/edit-manufacturer/{id}")
    public String editManufacturer(@PathVariable("id") Long id, Model model) {
        Manufacturer manufacturer = manufacturerService.findManufacturerById(id);
        if (manufacturer == null) {
            return "redirect:/manufacturers";
        }
        model.addAttribute("manufacturer", manufacturer);
        return "edit-manufacturer";
    }

    @PostMapping("/edit-manufacturer/{id}")
    public String saveChanges(@PathVariable("id") Long id, @ModelAttribute("manufacturer") Manufacturer manufacturer) {
        manufacturer.setId(id);
        manufacturerService.saveManufacturer(manufacturer);
        return "redirect:/manufacturers";
    }

    @PostMapping("/delete-manufacturer/{id}")
    public String deleteManufacturer(@PathVariable("id") Long id, Model model) {
        Manufacturer manufacturer = manufacturerService.findManufacturerById(id);
        if (manufacturer != null && !manufacturer.getProducts().isEmpty()) {
            model.addAttribute("error", "Valmistajalla on liitettyjä tuotteita. Poista ensin tuotteet.");
            model.addAttribute("manufacturers", manufacturerService.findAllManufacturers());
            return "manufacturers";
        }
        manufacturerService.deleteManufacturer(id);
        return "redirect:/manufacturers";
    }

}
