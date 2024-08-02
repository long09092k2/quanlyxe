package com.example.quanLyXeCRUD.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity {
    @Column(name = "created_at")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date creation_date;

    @Column(name = "updated_at")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date update_date;
}
