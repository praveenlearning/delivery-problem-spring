package com.example.everestproblem1spring.service;

import com.example.everestproblem1spring.model.Package;
import com.example.everestproblem1spring.model.PackageCostReport;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PackageService {

    @Value("${app.baseCost}")
    int baseCost;

    @Autowired
    private final OfferService offerService;

    public Package parsePackage(String input) {
        String[] packageInput = input.split(" ");
        String packageId = packageInput[0];
        int weight = Integer.parseInt(packageInput[1]);
        int distance = Integer.parseInt(packageInput[2]);
        String offerCode = null;
        try {
            offerCode = packageInput[3];
        } catch (ArrayIndexOutOfBoundsException ignored) {}
        return new Package(packageId, weight, distance, offerCode);
    }

    public int costFor(Package pkg) {
        return baseCost + pkg.getWeight() * 10 + pkg.getDistance() * 5;
    }

    public int discountFor(Package pkg) {
        int totalCost = costFor(pkg);
        return totalCost * offerService.applyOffer(pkg) / 100;
    }

    public int totalCost(Package pkg) {
        int totalCost = costFor(pkg);
        int discount = discountFor(pkg);

        return totalCost - discount;
    }

    public List<Package> parsePackages(String packagesInput) {
        String[] packagesInputArray = packagesInput.split("\n");

        return Arrays.stream(packagesInputArray)
                .map(this::parsePackage)
                .collect(Collectors.toList());
    }

    public List<PackageCostReport> costReport(List<Package> packages) {
        return packages.stream().map(pkg -> {
            int discount = discountFor(pkg);
            int totalCost = totalCost(pkg);
            return new PackageCostReport(pkg.getPackageId(), discount, totalCost);
        }).collect(Collectors.toList());
    }
}
