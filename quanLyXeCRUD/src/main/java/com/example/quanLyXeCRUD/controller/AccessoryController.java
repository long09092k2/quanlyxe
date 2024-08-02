package com.example.quanLyXeCRUD.controller;

import com.example.quanLyXeCRUD.entity.Accessory;
import com.example.quanLyXeCRUD.service.AccessoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accessories")
public class AccessoryController {
    @Autowired
    private AccessoryService accessoryService;

    @PostMapping
    public ResponseEntity<Accessory> createAccessory(@Valid @RequestBody Accessory accessory) {
        Accessory createdAccessory = accessoryService.createAccessory(accessory);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccessory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Accessory> updateAccessory(@PathVariable int id, @Valid @RequestBody Accessory accessory) {
        Accessory updatedAccessory = accessoryService.updateAccessory(id, accessory);
        return ResponseEntity.ok(updatedAccessory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccessory(@PathVariable int id) {
        accessoryService.deleteAccessory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Accessory> getAccessoryById(@PathVariable int id) {
        Accessory accessory = accessoryService.getAccessoryById(id);
        return ResponseEntity.ok(accessory);
    }

    @GetMapping
    public ResponseEntity<List<Accessory>> getAllAccessories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) List<Integer> categoryIds,
            @RequestParam(required = false) List<Integer> manufacturerIds,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {
        Page<Accessory> accessories = accessoryService.getAllAccessories(page, size, search, categoryIds, manufacturerIds, minPrice, maxPrice);
        return ResponseEntity.ok(accessories.getContent());
    }
}
