package com.example.demo;

import com.example.demo.models.Car;
import com.example.demo.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CarRepository carRepository;

    @Override
    public void run(String... args) throws Exception {
        loadCars();
    }

    private void loadCars() {
        List<Car> cars = Arrays.asList(
                new Car("Model1", "Series1"),
                new Car("Model2", "Series2"),
                new Car("Model3", "Series3"),
                new Car("Model4", "Series4"),
                new Car("Model5", "Series5"),
                new Car("Model6", "Series6"),
                new Car("Model7", "Series7"),
                new Car("Model8", "Series8"),
                new Car("Model9", "Series9"),
                new Car("Model10", "Series10"),
                new Car("Model11", "Series11")

        );

        carRepository.saveAll(cars);
    }
}
