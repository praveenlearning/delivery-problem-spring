package com.example.everestproblem1spring.service;

import com.example.everestproblem1spring.model.Package;
import com.example.everestproblem1spring.model.PackageCostReport;
import com.example.everestproblem1spring.model.PackageDeliveryReport;
import com.example.everestproblem1spring.model.Vehicle;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class VehicleService {

    @Getter
    private final List<Vehicle> vehicles = new ArrayList<>();

    private final DeliveryService deliveryService;

    private final PackageService packageService;

    public List<Vehicle> parseVehicles(String vehiclesInput) {
        String[] inputArray = vehiclesInput.split(" ");

        List<Vehicle> vehicles = new ArrayList<>();
        int nVehicles = Integer.parseInt(inputArray[0]);
        int maxSpeed = Integer.parseInt(inputArray[1]);
        int maxWeight = Integer.parseInt(inputArray[2]);
        for (int i = 0; i < nVehicles; i++) vehicles.add(new Vehicle(maxSpeed, maxWeight));

        return vehicles;
    }

    public void addVehicles(List<Vehicle> vehicles) {
        this.vehicles.addAll(vehicles);
    }

    public double calculateTime(Package pkg, Vehicle vehicle) {
        double value = (double) pkg.getDistance() / vehicle.getMaxSpeed();
        return vehicle.getAvailableIn() + BigDecimal.valueOf(value).setScale(2, RoundingMode.FLOOR).doubleValue();
    }

    public List<PackageDeliveryReport> dispatch(List<Package> packages) {
        List<PackageDeliveryReport> packageDeliveryReports = new ArrayList<>();

        while (packages.size() != 0 && vehicles.size() > 0) {
            Optional<Vehicle> vehicleOption = vehicles.stream()
                    .filter(Vehicle::isAvailable)
                    .min(Comparator.comparingDouble(Vehicle::getAvailableIn));

            if (vehicleOption.isEmpty()) break;
            Vehicle vehicle = vehicleOption.get();
            List<Package> packagesForVehicle = deliveryService.findPackagesForVehicle(packages, vehicle);

            if (!packagesForVehicle.isEmpty()) {
                List<PackageDeliveryReport> packageDeliveryReport = deliver(packagesForVehicle, vehicle);
                packageDeliveryReports.addAll(packageDeliveryReport);
            }

            if (packagesForVehicle.size() > 0) packages.removeAll(packagesForVehicle);
            else vehicle.setAvailable(false);
        }
        return packageDeliveryReports;
    }

    public List<PackageDeliveryReport> deliver(List<Package> packages, Vehicle vehicle) {
        List<PackageCostReport> packageCostReports = packageService.costReport(packages);

        List<PackageDeliveryReport> packageDeliveryReportStream = packageCostReports.stream()
                .map(report -> new PackageDeliveryReport(report, calculateTime(report.getAPackage(), vehicle)))
                .collect(Collectors.toList());

        double max = packageDeliveryReportStream.stream()
                .max(Comparator.comparingDouble(PackageDeliveryReport::getDeliveryTime))
                .get().getDeliveryTime();

        vehicle.setAvailableIn(vehicle.getAvailableIn() + 2 * max);

        return packageDeliveryReportStream;
    }
}
