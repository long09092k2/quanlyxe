package com.example.quanLyXeCRUD.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "tbl_attachment")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attachment {
    @Id
    @GeneratedValue
    private int id;
    private String source;
    private String extension;
    private String name;

    @ManyToOne
    @JoinColumn(name = "accessory_id")
    private Accessory accessory;
}
