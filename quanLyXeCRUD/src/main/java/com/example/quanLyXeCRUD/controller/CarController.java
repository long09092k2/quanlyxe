package com.example.quanLyXeCRUD.controller;

import com.example.quanLyXeCRUD.entity.Car;
import com.example.quanLyXeCRUD.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    @Autowired
    private CarService carService;

    @PostMapping
    public ResponseEntity<Car> createCar(@Valid @RequestBody Car car) {
        Car createdCar = carService.createCar(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable int id, @Valid @RequestBody Car car) {
        Car updatedCar = carService.updateCar(id, car);
        return ResponseEntity.ok(updatedCar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable int id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable int id) {
        Car car = carService.getCarById(id);
        return ResponseEntity.ok(car);
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) List<Integer> manufacturerIds,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        Page<Car> cars = carService.getAllCars(page, size, search, manufacturerIds, startDate, endDate);
        return ResponseEntity.ok(cars.getContent());
    }
}
