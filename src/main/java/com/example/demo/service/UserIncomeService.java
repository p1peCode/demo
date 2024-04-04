package com.example.demo.service;

import com.example.demo.models.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserIncomeService {
    private final String INCOME = "https://66055cd12ca9478ea1801f2e.mockapi.io/api/users/income";

    public int getUserIncome(int userId) {
        RestTemplate restTemplate = new RestTemplate();
        User[] userIncomes = restTemplate.getForObject(INCOME, User[].class);
        if (userIncomes != null) {
            for (User user : userIncomes) {
                if (user.getId() == userId) {
                    return user.getIncome();
                }
            }
        }
        return 0;
    }
}
