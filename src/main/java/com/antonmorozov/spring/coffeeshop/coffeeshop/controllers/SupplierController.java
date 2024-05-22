package com.antonmorozov.spring.coffeeshop.coffeeshop.controllers;

import com.antonmorozov.spring.coffeeshop.coffeeshop.models.Supplier;
import com.antonmorozov.spring.coffeeshop.coffeeshop.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SupplierController {

    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/suppliers")
    public String suppliers(Model model) {
        model.addAttribute("suppliers", supplierService.findAllSuppliers());
        return "suppliers";
    }

    @PostMapping("/add-supplier")
    public String addSupplier(@ModelAttribute Supplier supplier) {
        supplierService.saveSupplier(supplier);
        return "redirect:/suppliers";
    }

    @GetMapping("/edit-supplier/{id}")
    public String showEditingForm(@PathVariable("id") String id, Model model) {
        try {
            Long supplierId = Long.parseLong(id);
            Supplier supplier = supplierService.findSupplierById(supplierId);
            if (supplier == null) {
                return "redirect:/suppliers";
            }
            model.addAttribute("supplier", supplier);
            return "edit-supplier";
        } catch (NumberFormatException e) {
            return "redirect:/suppliers";
        }
    }

    @PostMapping("/edit-supplier/{id}")
    public String saveChanges(@PathVariable("id") Long id, @ModelAttribute("supplier") Supplier supplier) {
        supplier.setId(id);
        supplierService.saveSupplier(supplier);
        return "redirect:/suppliers";
    }

    @PostMapping("/delete-supplier/{id}")
    public String deleteSupplier(@PathVariable("id") Long id, Model model) {
        Supplier supplier = supplierService.findSupplierById(id);
        if (supplier != null && !supplier.getProducts().isEmpty()) {
            model.addAttribute("error", "Toimittajalla on liitettyjä tuotteita. Poista ensin tuotteet.");
            model.addAttribute("suppliers", supplierService.findAllSuppliers());
            return "suppliers";
        }
        supplierService.deleteSupplier(id);
        return "redirect:/suppliers";
    }

}
