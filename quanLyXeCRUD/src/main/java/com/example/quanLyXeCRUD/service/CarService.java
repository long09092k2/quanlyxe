package com.example.quanLyXeCRUD.service;

import com.example.quanLyXeCRUD.entity.Car;
import com.example.quanLyXeCRUD.entity.Manufacturer;
import com.example.quanLyXeCRUD.exception.ResourceNotFoundException;
import com.example.quanLyXeCRUD.repository.CarRepository;
import com.example.quanLyXeCRUD.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    public Car createCar(Car car) {
        // Validate manufacturer
        Manufacturer manufacturer = manufacturerRepository.findById(car.getManufacturer().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Manufacturer not found"));

        // Validate car name
        if (carRepository.existsByNameAndManufacturer(car.getName(), manufacturer)) {
            throw new IllegalArgumentException("Car name already exists for the selected manufacturer.");
        }

        car.setManufacturer(manufacturer);
        return carRepository.save(car);
    }

    public Car updateCar(int id, Car car) {
        Car existingCar = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found"));

        // Validate car name
        if (!existingCar.getName().equals(car.getName()) &&
                carRepository.existsByNameAndManufacturer(car.getName(), existingCar.getManufacturer())) {
            throw new IllegalArgumentException("Car name already exists for the selected manufacturer.");
        }

        existingCar.setName(car.getName());
        existingCar.setUpdate_user(car.getUpdate_user());
        existingCar.setIs_deleted(car.isIs_deleted());

        return carRepository.save(existingCar);
    }

    public void deleteCar(int id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found"));

        // Validate if car has accessories
        if (!car.getAccessories().isEmpty()) {
            throw new IllegalArgumentException("Cannot delete car with accessories.");
        }

        carRepository.delete(car);
    }

    public Car getCarById(int id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found"));
    }

    public Page<Car> getAllCars(int page, int size, String search, List<Integer> manufacturerIds, String startDate, String endDate) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("updateDate").descending());
        // Implement search and filter logic
        return carRepository.findAll(pageable);
    }
}

