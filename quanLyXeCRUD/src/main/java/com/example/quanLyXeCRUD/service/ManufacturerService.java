package com.example.quanLyXeCRUD.service;

import com.example.quanLyXeCRUD.entity.Manufacturer;
import com.example.quanLyXeCRUD.exception.ResourceNotFoundException;
import com.example.quanLyXeCRUD.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerService {
    @Autowired
    private ManufacturerRepository manufacturerRepository;

    public Manufacturer createManufacturer(Manufacturer manufacturer) {
        // Validate name
        if (manufacturerRepository.existsByName(manufacturer.getName())) {
            throw new IllegalArgumentException("Manufacturer name already exists.");
        }
        return manufacturerRepository.save(manufacturer);
    }

    public Manufacturer updateManufacturer(int id, Manufacturer manufacturer) {
        Manufacturer existingManufacturer = manufacturerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Manufacturer not found"));

        // Validate name
        if (!existingManufacturer.getName().equals(manufacturer.getName()) &&
                manufacturerRepository.existsByName(manufacturer.getName())) {
            throw new IllegalArgumentException("Manufacturer name already exists.");
        }

        existingManufacturer.setName(manufacturer.getName());
        existingManufacturer.setUpdate_user(manufacturer.getUpdate_user());
        existingManufacturer.setIs_deleted(manufacturer.isIs_deleted());

        return manufacturerRepository.save(existingManufacturer);
    }

    public void deleteManufacturer(int id) {
        Manufacturer manufacturer = manufacturerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Manufacturer not found"));

        // Validate if manufacturer has cars with accessories
        if (manufacturer.getCars().stream().anyMatch(car -> !car.getAccessories().isEmpty())) {
            throw new IllegalArgumentException("Cannot delete manufacturer with cars having accessories.");
        }

        manufacturerRepository.delete(manufacturer);
    }

    public List<Manufacturer> getAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    public Manufacturer getManufacturerById(int id) {
        return manufacturerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Manufacturer not found"));
    }
}

