package com.example.quanLyXeCRUD.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity(name = "tbl_category")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category extends AbstractEntity{
    @Id
    @GeneratedValue
    private int id;
    private String name;


    private String creation_user;
    private String update_user;
    private boolean is_deleted;



    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Accessory> accessories;


}
