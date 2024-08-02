package com.example.quanLyXeCRUD.repository;

import com.example.quanLyXeCRUD.entity.Car;
import com.example.quanLyXeCRUD.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    boolean existsByNameAndManufacturer(String name, Manufacturer manufacturer);
}
