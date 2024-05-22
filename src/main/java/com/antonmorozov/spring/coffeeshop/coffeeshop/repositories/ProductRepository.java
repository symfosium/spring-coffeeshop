package com.antonmorozov.spring.coffeeshop.coffeeshop.repositories;

import com.antonmorozov.spring.coffeeshop.coffeeshop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT t FROM Product t JOIN FETCH t.department o WHERE o.id = :departmentID OR o.departmentIDP = :departmentID")
    List<Product> findProductsByDepartmentID(@Param("departmentID") Long departmentID);

}
