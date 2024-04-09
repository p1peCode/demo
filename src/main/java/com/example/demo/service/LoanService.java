package com.example.demo.service;

import com.example.demo.properties.LoanConfig;
import com.example.demo.repositories.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoanService {

    private final UserIncomeService userIncomeService;
    private final CarRepository carRepository;

    @Autowired
    private final LoanConfig loanConfig;

    public int approveLoan(int userId) {
        int userIncome = userIncomeService.getUserIncome(userId);
        int delimeter = loanConfig.getDelimeterOfUserIncome();
        int minIncome = loanConfig.getMinIncome();
        int minimalCostOfCar = loanConfig.getMinimalCostOfCar();
        float maxCoefficientOfLoanOnCar = loanConfig.getMaxCoefficientOfLoanOnCar();

        if (userIncome < minIncome && carRepository.findByUserId(userId).getPrice() < minimalCostOfCar) {
            return 0;
        }

        if (carRepository.findByUserId(userId) != null) {
            int priceOfCar = carRepository.findByUserId(userId).getPrice();
            return (int) Math.max((userIncome / delimeter), (priceOfCar * maxCoefficientOfLoanOnCar));
        }
        return userIncome / delimeter;
    }
}