package com.antonmorozov.spring.coffeeshop.coffeeshop.services;

import com.antonmorozov.spring.coffeeshop.coffeeshop.models.Manufacturer;
import com.antonmorozov.spring.coffeeshop.coffeeshop.repositories.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    @Autowired
    public ManufacturerService(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    public List<Manufacturer> findAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    public Manufacturer findManufacturerById(Long id) {
        return manufacturerRepository.findById(id).orElse(null);
    }

    public void saveManufacturer(Manufacturer manufacturer) {
        manufacturerRepository.save(manufacturer);
    }

    public void deleteManufacturer(Long id) {
        manufacturerRepository.deleteById(id);
    }

    public void updateManufacturer(Long id, Manufacturer manufacturer) {
        Optional<Manufacturer> existingManufacturerOptional = manufacturerRepository.findById(id);
        if (existingManufacturerOptional.isPresent()) {
            Manufacturer existingManufacturer = existingManufacturerOptional.get();
            existingManufacturer.setName(manufacturer.getName());
            existingManufacturer.setUrl(manufacturer.getUrl());
            manufacturerRepository.save(existingManufacturer);
        } else {
            throw new IllegalArgumentException("Manufacturer not found with id: " + id);
        }
    }
}
