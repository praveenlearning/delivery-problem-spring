package com.example.everestproblem1spring.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.Getter;

@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class Package {
    private final String packageId;
    private final int weight;
    private final int distance;
    private String offerCode;
}
