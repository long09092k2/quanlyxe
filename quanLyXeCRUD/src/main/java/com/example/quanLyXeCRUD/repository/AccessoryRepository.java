package com.example.quanLyXeCRUD.repository;

import com.example.quanLyXeCRUD.entity.Accessory;
import com.example.quanLyXeCRUD.entity.Car;
import com.example.quanLyXeCRUD.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessoryRepository extends JpaRepository<Accessory, Integer> {
    boolean existsByNameAndCarAndManufacturer(String name, Car car, Manufacturer manufacturer);
    boolean existsByCodeAndCarAndManufacturer(String code, Car car, Manufacturer manufacturer);
}
