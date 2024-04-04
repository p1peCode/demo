package com.example.demo.service;

import com.example.demo.models.Car;
import com.example.demo.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

    private final UserIncomeService userIncomeService;
    private final CarRepository carRepository;

    private Car car;

    @Value("${minIncome}")
    private int minIncome;

    @Autowired
    public LoanService(UserIncomeService userIncomeService, CarRepository carRepository) {
        this.userIncomeService = userIncomeService;
        this.carRepository = carRepository;
    }

    public int approveLoan(int userId) {
        int userIncome = userIncomeService.getUserIncome(userId);
        if (userIncome > minIncome) {
            return userIncome / 2;
        } else {
            int carCost = carRepository.findByUserId(userId).getPrice();
            int maxLoanFromCar = (int) (carCost * 0.3);
            return maxLoanFromCar;
        }
    }
}
