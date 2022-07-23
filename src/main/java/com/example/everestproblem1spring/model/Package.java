package com.example.everestproblem1spring.model;

import lombok.Data;

@Data
public class Package {
    final String packageId;
    final int weight;
    final int distance;
    final String offerCode;
}
