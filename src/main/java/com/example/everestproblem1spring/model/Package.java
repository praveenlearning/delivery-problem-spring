package com.example.everestproblem1spring.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Package {
    final String packageId;
    final int weight;
    final int distance;
    String offerCode;
}
