package com.example.quanLyXeCRUD.service;

import com.example.quanLyXeCRUD.entity.Accessory;
import com.example.quanLyXeCRUD.entity.Car;
import com.example.quanLyXeCRUD.entity.Manufacturer;
import com.example.quanLyXeCRUD.exception.ResourceNotFoundException;
import com.example.quanLyXeCRUD.repository.AccessoryRepository;
import com.example.quanLyXeCRUD.repository.CarRepository;
import com.example.quanLyXeCRUD.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccessoryService {
    @Autowired
    private AccessoryRepository accessoryRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Autowired
    private CarRepository carRepository;

    public Accessory createAccessory(Accessory accessory) {
        // Validate manufacturer
        Manufacturer manufacturer = manufacturerRepository.findById(accessory.getManufacturer().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Manufacturer not found"));

        // Validate car
        Car car = carRepository.findById(accessory.getCar().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Car not found"));

        if (!car.getManufacturer().equals(manufacturer)) {
            throw new IllegalArgumentException("Car does not belong to the selected manufacturer.");
        }

        // Validate name and code
        if (accessoryRepository.existsByNameAndCarAndManufacturer(accessory.getName(), car, manufacturer) ||
                accessoryRepository.existsByCodeAndCarAndManufacturer(accessory.getCode(), car, manufacturer)) {
            throw new IllegalArgumentException("Accessory name or code already exists for the selected car and manufacturer.");
        }

        return accessoryRepository.save(accessory);
    }

    public Accessory updateAccessory(int id, Accessory accessory) {
        Accessory existingAccessory = accessoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accessory not found"));

        // Validate name and code
        if (!existingAccessory.getName().equals(accessory.getName()) &&
                accessoryRepository.existsByNameAndCarAndManufacturer(accessory.getName(), existingAccessory.getCar(), existingAccessory.getManufacturer())) {
            throw new IllegalArgumentException("Accessory name already exists for the selected car and manufacturer.");
        }

        if (!existingAccessory.getCode().equals(accessory.getCode()) &&
                accessoryRepository.existsByCodeAndCarAndManufacturer(accessory.getCode(), existingAccessory.getCar(), existingAccessory.getManufacturer())) {
            throw new IllegalArgumentException("Accessory code already exists for the selected car and manufacturer.");
        }

        existingAccessory.setName(accessory.getName());
        existingAccessory.setDescription(accessory.getDescription());
        existingAccessory.setPrice(accessory.getPrice());
        existingAccessory.setUpdate_user(accessory.getUpdate_user());
        existingAccessory.set_deleted(accessory.is_deleted());

        return accessoryRepository.save(existingAccessory);
    }

    public void deleteAccessory(int id) {
        Accessory accessory = accessoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accessory not found"));

        accessoryRepository.delete(accessory);
    }

    public Accessory getAccessoryById(int id) {
        return accessoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accessory not found"));
    }

    public Page<Accessory> getAllAccessories(int page, int size, String search, List<Integer> categoryIds, List<Integer> manufacturerIds, Double minPrice, Double maxPrice) {
        Pageable pageable = PageRequest.of(page, size);
        // Implement search and filter logic
        return accessoryRepository.findAll(pageable);
    }
}

