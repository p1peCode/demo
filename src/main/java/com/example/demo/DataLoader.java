package com.example.demo;

import com.example.demo.model.Car;
import com.example.demo.model.User;
import com.example.demo.repository.CarRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    @Override
    public void run(String... args) {
        loadCarsAndUsers();
    }

    private void loadCarsAndUsers() {
        for (int i = 1; i <= 10; i++) {
            User user = new User();
            user.setName("User" + i);
            user.setId(i);
            userRepository.save(user);
            Car car = new Car();
            car.setModel("Model" + i);
            car.setSeries("Series" + i);
            car.setPrice((int) (Math.random() * 1_500_000));
            car.setUser(user);
            carRepository.save(car);
        }
    }
}