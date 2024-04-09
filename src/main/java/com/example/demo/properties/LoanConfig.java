package com.example.demo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "loan")
public class LoanConfig {

    private int minIncome;
    private float maxCoefficientOfLoanOnCar;
    private int minimalCostOfCar;
    private int delimeterOfUserIncome;
}
