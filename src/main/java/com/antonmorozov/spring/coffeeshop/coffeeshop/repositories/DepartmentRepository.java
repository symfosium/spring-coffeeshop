package com.antonmorozov.spring.coffeeshop.coffeeshop.repositories;

import com.antonmorozov.spring.coffeeshop.coffeeshop.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
