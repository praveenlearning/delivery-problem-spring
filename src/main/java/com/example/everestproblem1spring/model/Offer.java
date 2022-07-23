package com.example.everestproblem1spring.model;

import lombok.AllArgsConstructor;

import java.util.function.Predicate;

@AllArgsConstructor
public enum Offer {
    OFR001(pkg -> pkg.getDistance() < 200 && pkg.getWeight() >= 70 && pkg. getWeight() <= 200, 10),
    OFR002(pkg -> pkg.getDistance() >= 50 && pkg.getDistance() <= 150 && pkg.getWeight() >= 70 && pkg. getWeight() <= 200, 7),
    OFR003(pkg -> pkg.getDistance() >= 50 && pkg.getDistance() <= 250 && pkg.getWeight() >= 10 && pkg. getWeight() <= 150, 5),
    NONE(pkg -> false, 0);

    private final Predicate<Package> predicate;
    private final int discount;

    public int apply(Package pkg) {
        return predicate.test(pkg) ? discount : 0;
    }
}
