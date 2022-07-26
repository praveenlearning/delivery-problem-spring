package com.example.everestproblem1spring.model;

import lombok.Data;

@Data
public class Vehicle {
    final int maxSpeed;
    final int maxCapacity;
    private boolean isAvailable = true;
    private double availableIn = 0;
}
