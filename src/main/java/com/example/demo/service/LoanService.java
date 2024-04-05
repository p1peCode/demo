package com.example.demo.service;

import com.example.demo.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

    private final UserIncomeService userIncomeService;
    private final CarRepository carRepository;

    @Value("${minIncome}")
    private int minIncome;

    @Autowired
    public LoanService(UserIncomeService userIncomeService, CarRepository carRepository) {
        this.userIncomeService = userIncomeService;
        this.carRepository = carRepository;
    }

    public int approveLoan(int userId) {
        int userIncome = userIncomeService.getUserIncome(userId) / 2;
        if (carRepository.findByUserId(userId) != null) {
            int carCost = (int) (carRepository.findByUserId(userId).getPrice() * 0.3);
            return Math.max(userIncome, carCost);
        }
        return userIncome;
    }
}