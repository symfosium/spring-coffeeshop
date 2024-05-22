package com.antonmorozov.spring.coffeeshop.coffeeshop.services;

import com.antonmorozov.spring.coffeeshop.coffeeshop.models.Department;
import com.antonmorozov.spring.coffeeshop.coffeeshop.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> findAllDepartments() {
        return departmentRepository.findAll();
    }

    public void saveDepartment(Department department) {
        departmentRepository.save(department);
    }
}
