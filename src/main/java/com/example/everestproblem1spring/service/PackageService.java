package com.example.everestproblem1spring.service;

import com.example.everestproblem1spring.model.Package;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PackageService {

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
        return pkg.getWeight() * 10 + pkg.getDistance() * 5;
    }

    public int discountFor(Package pkg) {
        return offerService.applyOffer(pkg);
    }
}
