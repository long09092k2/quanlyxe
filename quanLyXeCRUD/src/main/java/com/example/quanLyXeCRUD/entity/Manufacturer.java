package com.example.quanLyXeCRUD.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity(name = "tbl_manufacturer")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Manufacturer extends AbstractEntity{
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

    @OneToMany(mappedBy = "manufacturer")
    private List<Car> cars;

    @OneToMany(mappedBy = "manufacturer")
    private List<Accessory> accessories;


}
