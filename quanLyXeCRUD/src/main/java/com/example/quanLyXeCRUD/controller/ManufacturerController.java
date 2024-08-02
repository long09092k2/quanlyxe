package com.example.quanLyXeCRUD.controller;

import com.example.quanLyXeCRUD.entity.Manufacturer;
import com.example.quanLyXeCRUD.service.ManufacturerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manufacturers")
public class ManufacturerController {
    @Autowired
    private ManufacturerService manufacturerService;

    @PostMapping
    public ResponseEntity<Manufacturer> createManufacturer(@Valid @RequestBody Manufacturer manufacturer) {
        Manufacturer createdManufacturer = manufacturerService.createManufacturer(manufacturer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdManufacturer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Manufacturer> updateManufacturer(@PathVariable int id, @Valid @RequestBody Manufacturer manufacturer) {
        Manufacturer updatedManufacturer = manufacturerService.updateManufacturer(id, manufacturer);
        return ResponseEntity.ok(updatedManufacturer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManufacturer(@PathVariable int id) {
        manufacturerService.deleteManufacturer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Manufacturer>> getAllManufacturers() {
        List<Manufacturer> manufacturers = manufacturerService.getAllManufacturers();
        return ResponseEntity.ok(manufacturers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manufacturer> getManufacturerById(@PathVariable int id) {
        Manufacturer manufacturer = manufacturerService.getManufacturerById(id);
        return ResponseEntity.ok(manufacturer);
    }
}
