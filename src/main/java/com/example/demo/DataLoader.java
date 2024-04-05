package com.example.demo;

import com.example.demo.models.Car;
import com.example.demo.models.User;
import com.example.demo.repositories.CarRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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
        RestTemplate restTemplate = new RestTemplate();
        User[] usersIncome = restTemplate.getForObject("https://66055cd12ca9478ea1801f2e.mockapi.io/api/users/income", User[].class);

        for (int i = usersIncome.length; i > 0; i--) {
            User user = new User();
            user.setName("User" + usersIncome[i-1].getId());
            user.setIncome(usersIncome[i-1].getIncome());
            user.setId(usersIncome[i-1].getId());
            userRepository.save(user);
        }

        Car car1 = new Car();
        car1.setModel("Model1");
        car1.setSeries("Series1");
        car1.setPrice(5_000_000);
        car1.setUser(usersIncome[1]);
        carRepository.save(car1);

        Car car2 = new Car();
        car2.setModel("Model2");
        car2.setSeries("Series2");
        car2.setPrice(503_000);
        car2.setUser(usersIncome[5]);
        carRepository.save(car2);
    }
}
