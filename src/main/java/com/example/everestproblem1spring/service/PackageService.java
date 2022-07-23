package com.example.everestproblem1spring.service;

import com.example.everestproblem1spring.model.Package;
import org.springframework.stereotype.Service;

@Service
public class PackageService {

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
}
