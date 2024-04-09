package com.example.demo.service;

import com.example.demo.property.LoanProperties;
import com.example.demo.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoanService {

    private final UserIncomeService userIncomeService;
    private final CarRepository carRepository;
    private final LoanProperties loanProperties;

    public int approveLoan(int userId) {
        int userIncome = userIncomeService.getUserIncome(userId);
        int divisor = loanProperties.getUserIncomeDivisor();
        int minIncome = loanProperties.getMinIncome();
        int minimalCostOfCar = loanProperties.getMinimalCostOfCar();
        float maxCoefficientOfLoanOnCar = loanProperties.getMaxCoefficientOfLoanOnCar();
        int costOfCar = carRepository.findByUserId(userId).getPrice();

        if (userIncome < minIncome && costOfCar < minimalCostOfCar) {
            return 0;
        }

        if (userIncome < minIncome && costOfCar > minimalCostOfCar) {
            return (int) (costOfCar * 0.3);
        }

        if (carRepository.findByUserId(userId) != null) {
            return (int) Math.max((userIncome / divisor), (costOfCar * maxCoefficientOfLoanOnCar));
        }
        return userIncome / divisor;
    }
}