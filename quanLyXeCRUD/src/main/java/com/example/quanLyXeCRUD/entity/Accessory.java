package com.example.quanLyXeCRUD.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Entity(name = "tbl_accessory")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Accessory extends AbstractEntity{
    @Id
    @GeneratedValue
    private int id;
    private String code;
    private String name;
    private String description;
    private double price;
    private String creation_user;
    private String update_user;
    private boolean is_deleted;


    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @ManyToMany(mappedBy = "accessories")
    private List<Car> cars;

    @OneToMany(mappedBy = "accessory")
    private List<Attachment> attachments;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;



}
