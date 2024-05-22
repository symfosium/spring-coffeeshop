package com.antonmorozov.spring.coffeeshop.coffeeshop.repositories;

import com.antonmorozov.spring.coffeeshop.coffeeshop.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
