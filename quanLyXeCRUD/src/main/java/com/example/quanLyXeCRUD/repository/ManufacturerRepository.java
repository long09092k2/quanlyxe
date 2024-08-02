package com.example.quanLyXeCRUD.repository;

import com.example.quanLyXeCRUD.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer> {
    boolean existsByName(String name);
}
