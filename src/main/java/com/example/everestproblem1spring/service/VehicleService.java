package com.example.everestproblem1spring.service;

import com.example.everestproblem1spring.model.Vehicle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {

    public List<Vehicle> parseVehicles(String vehiclesInput) {
        String[] inputArray = vehiclesInput.split(" ");

        List<Vehicle> vehicles = new ArrayList<>();
        int nVehicles = Integer.parseInt(inputArray[0]);
        int maxSpeed = Integer.parseInt(inputArray[1]);
        int maxWeight = Integer.parseInt(inputArray[2]);
        for (int i = 0; i < nVehicles; i++) vehicles.add(new Vehicle(maxSpeed, maxWeight));

        return vehicles;
    }
}