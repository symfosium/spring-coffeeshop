package com.antonmorozov.spring.coffeeshop.coffeeshop.controllers;

import com.antonmorozov.spring.coffeeshop.coffeeshop.models.Product;
import com.antonmorozov.spring.coffeeshop.coffeeshop.services.DepartmentService;
import com.antonmorozov.spring.coffeeshop.coffeeshop.services.ManufacturerService;
import com.antonmorozov.spring.coffeeshop.coffeeshop.services.ProductService;
import com.antonmorozov.spring.coffeeshop.coffeeshop.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class    ProductController {

    private final ProductService productService;
    private final ManufacturerService manufacturerService;
    private final SupplierService supplierService;
    private final DepartmentService departmentService;

    @Autowired
    public ProductController(ProductService productService, ManufacturerService manufacturerService,
                           SupplierService supplierService, DepartmentService departmentService) {
        this.productService = productService;
        this.manufacturerService = manufacturerService;
        this.supplierService = supplierService;
        this.departmentService = departmentService;
    }

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productService.findAllProducts());
        model.addAttribute("manufacturers", manufacturerService.findAllManufacturers());
        model.addAttribute("suppliers", supplierService.findAllSuppliers());
        model.addAttribute("departments", departmentService.findAllDepartments());
        return "products";
    }

    @PostMapping("/add-product")
    public String lisaaTuote(@ModelAttribute Product product, @RequestParam("picture") MultipartFile file) throws IOException {
        productService.saveProduct(product, file);
        return "redirect:/products";
    }

    @PostMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @GetMapping("/productImage/{id}")
    public ResponseEntity<byte[]> getProductImage(@PathVariable Long id) {
        Product product = productService.findProductById(id);
        if (product != null && product.getProductPicture() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(product.getProductPicture(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
