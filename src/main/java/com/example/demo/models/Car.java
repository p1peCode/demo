package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column
    private String model;

    @Column
    private String series;

    @Column
    private int price;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Car(String model, String series, int price) {
        this.model = model;
        this.series = series;
        this.price = price;
    }

    public Car() {

    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Car|" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", series='" + series + '\'' +
                '}';
    }
}