package com.antonmorozov.spring.coffeeshop.coffeeshop.repositories;

import com.antonmorozov.spring.coffeeshop.coffeeshop.models.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
}