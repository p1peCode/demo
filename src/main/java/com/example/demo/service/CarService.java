package com.example.demo.service;

import com.example.demo.model.Car;
import com.example.demo.property.CarsProperties;
import com.example.demo.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final CarsProperties carsProperties;

    public List<Car> getCarsFromDb() {
        return carRepository.findAll();
    }

    public List<Car> getCars(int count) {
        int maxCars = carsProperties.getMaxCars();
        if (count == 0 || count >= maxCars) {
            return getCarsFromDb();
        } else {
            Pageable pageable = PageRequest.of(0, count);
            return carRepository.findAll(pageable).toList();
        }
    }
}
