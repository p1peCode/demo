package com.example.demo.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserIncomeService {
    private final String API = "https://66055cd12ca9478ea1801f2e.mockapi.io/api/users/income";
    private final String ID = "id";
    private final String INCOME = "income";
    public int getUserIncome(int userId) {
        RestTemplate restTemplate = new RestTemplate();
        JsonNode[] users = restTemplate.getForObject(API, JsonNode[].class);

        if (users != null) {
            for (JsonNode user : users) {
                if (user.has(ID) && user.get(ID).asInt() == userId && user.has(INCOME)) {
                    return user.get(INCOME).asInt();
                }
            }
        }
        return 0;
    }
}