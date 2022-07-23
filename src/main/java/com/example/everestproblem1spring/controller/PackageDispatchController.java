package com.example.everestproblem1spring.controller;

import com.example.everestproblem1spring.model.Package;
import com.example.everestproblem1spring.model.PackageCostReport;
import com.example.everestproblem1spring.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/package")
public class PackageDispatchController {

    @Autowired
    private PackageService packageService;

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
}
