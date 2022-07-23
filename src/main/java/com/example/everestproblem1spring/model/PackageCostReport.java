package com.example.everestproblem1spring.model;

import lombok.Data;

@Data
public class PackageCostReport {
    private final String packageId;
    private final int discount;
    private final int cost;
}
