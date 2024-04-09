package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    private int id;

    @Column
    private String name;

    @OneToOne(mappedBy = "user")
    @JoinColumn(name = "car_id")
    private Car car;
}