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
        int delimeter = loanProperties.getDelimeterOfUserIncome();
        int minIncome = loanProperties.getMinIncome();
        int minimalCostOfCar = loanProperties.getMinimalCostOfCar();
        float maxCoefficientOfLoanOnCar = loanProperties.getMaxCoefficientOfLoanOnCar();

        if (userIncome < minIncome && carRepository.findByUserId(userId).getPrice() < minimalCostOfCar) {
            return 0;
        }

        int costOfCar = carRepository.findByUserId(userId).getPrice();
        if (userIncome < minIncome && costOfCar > minimalCostOfCar) {
            return (int) (costOfCar * 0.3);
        }

        if (carRepository.findByUserId(userId) != null) {
            int priceOfCar = carRepository.findByUserId(userId).getPrice();
            return (int) Math.max((userIncome / delimeter), (priceOfCar * maxCoefficientOfLoanOnCar));
        }
        return userIncome / delimeter;
    }
}