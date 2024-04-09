package com.example.demo.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "loan")
public class LoanProperties {

    private int minIncome;
    private float maxCoefficientOfLoanOnCar;
    private int minimalCostOfCar;
    private int userIncomeDivisor;
}
