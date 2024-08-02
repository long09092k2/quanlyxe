package com.example.quanLyXeCRUD.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity(name = "tbl_user")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractEntity{
    @Id
    private String username;
    private String password;
    private String name;
    private String position;

    private boolean is_deleted;
}
