package com.example.everestproblem1spring.model;

import lombok.Data;

@Data
public class PackageDeliveryReport {
    final PackageCostReport costReport;
    final double deliveryTime;
}
