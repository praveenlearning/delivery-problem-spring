package com.example.everestproblem1spring.service;

import com.example.everestproblem1spring.model.Vehicle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class VehicleServiceTest {

    private PackageService packageService;
    private DeliveryService deliveryService;
    private VehicleService vehicleService;

    @BeforeAll
    void setUp() {
        packageService = mock(PackageService.class);
        deliveryService = mock(DeliveryService.class);
        vehicleService = new VehicleService(deliveryService, packageService);
    }

    @Test
    void parseVehicles() {
        var expected = new ArrayList<>() {{
            add(new Vehicle(70, 200));
            add(new Vehicle(70, 200));
        }};

        List<Vehicle> vehicles = vehicleService.parseVehicles("2 70 200");

        assertEquals(expected, vehicles);
    }

    @Test
    void parseVehiclesShouldThrowExceptionForInvalidInput() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> vehicleService.parseVehicles("2"));
        assertThrows(NumberFormatException.class, () -> vehicleService.parseVehicles("T"));
    }

    @Test
    void addVehicles() {
        List<Vehicle> vehicles = vehicleService.parseVehicles("2 70 200");
        vehicleService.addVehicles(vehicles);

        List<Vehicle> vehiclesAfter = vehicleService.getVehicles();

        assertEquals(vehicles, vehiclesAfter);
        assertTrue(vehiclesAfter.containsAll(vehicles));
    }
}
