package com.antonmorozov.spring.coffeeshop.coffeeshop.services;

import com.antonmorozov.spring.coffeeshop.coffeeshop.models.Product;
import com.antonmorozov.spring.coffeeshop.coffeeshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public void saveProduct(Product tuote, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            tuote.setTuotekuva(file.getBytes());
            productRepository.save(tuote);
        } else {
            throw new IllegalArgumentException("File is empty or null");
        }
    }

    public List<Product> getCoffeeMachines(Long departmentID) {
        return productRepository.findProductsByDepartmentID(departmentID);
    }

    public void deleteProduct(Long tuoteId) {
        productRepository.deleteById(tuoteId);
    }

    public Product findProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}