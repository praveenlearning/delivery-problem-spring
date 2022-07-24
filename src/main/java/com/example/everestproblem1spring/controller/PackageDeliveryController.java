package com.example.everestproblem1spring.controller;

import com.example.everestproblem1spring.model.Package;
import com.example.everestproblem1spring.model.PackageCostReport;
import com.example.everestproblem1spring.model.PackageDeliveryReport;
import com.example.everestproblem1spring.model.Vehicle;
import com.example.everestproblem1spring.service.PackageService;
import com.example.everestproblem1spring.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/package")
public class PackageDeliveryController {

    @Autowired
    private PackageService packageService;

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/cost")
    public ResponseEntity<List<PackageCostReport>> calculateCost(@RequestBody String input) {
        List<Package> packages = packageService.parsePackages(input);
        List<PackageCostReport> costReport = packageService.costReport(packages);

        return new ResponseEntity<>(costReport, HttpStatus.OK);
    }

    @GetMapping("/costWithPackageObjects")
    public ResponseEntity<List<PackageCostReport>> calculateCost(@RequestBody List<Package> packages) {
        List<PackageCostReport> costReport = packageService.costReport(packages);
        return new ResponseEntity<>(costReport, HttpStatus.OK);
    }

    @GetMapping("time")
    public ResponseEntity<List<PackageDeliveryReport>> estimateDelivery(@RequestBody List<Package> packages) {
        List<PackageDeliveryReport> reports = vehicleService.dispatch(packages);
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @PostMapping("addVehicles")
    public List<Vehicle> addVehicles(@RequestBody String vehiclesInput) {
        List<Vehicle> vehicles = vehicleService.parseVehicles(vehiclesInput);
        vehicleService.addVehicles(vehicles);
        return vehicles;
    }
}
