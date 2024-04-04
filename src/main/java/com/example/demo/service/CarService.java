package com.example.demo.service;

import com.example.demo.models.Car;
import com.example.demo.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Value("${maxCars}")
    private int maxCars;

    public List<Car> getCarsFromDb() {
        return carRepository.findAll();
    }

    public List<Car> getCars(int count) {
        if (count == 0 || count >= maxCars) {
            return getCarsFromDb();
        } else {
            Pageable pageable = PageRequest.of(0, count);
            return carRepository.findAll(pageable).toList();
        }
    }
}
