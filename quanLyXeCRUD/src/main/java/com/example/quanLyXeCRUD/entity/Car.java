package com.example.quanLyXeCRUD.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Entity(name = "tbl_car")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car extends AbstractEntity{
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String creation_user;
    private String update_user;
    private boolean is_deleted;

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "car_accessory" ,
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "accessory_id")
    )
    private List<Accessory> accessories ;
}
