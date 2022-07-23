package com.example.everestproblem1spring.service;

import com.example.everestproblem1spring.model.Package;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PackageServiceTest {

    private OfferService offerService;
    private PackageService packageService;

    @BeforeAll
    void setUp() {
        offerService = mock(OfferService.class);
        packageService = new PackageService(offerService);
    }

    @Test
    void shouldParsePackageFromString() {
        Package aPackage = packageService.parsePackage("PKG1 50 30 OFR001");
        Package expected = new Package("PKG1", 50, 30, "OFR001");

        assertEquals(expected, aPackage);
    }

    @Test
    void shouldParsePackageFromStringWithoutOfferCode() {
        Package aPackage = packageService.parsePackage("PKG1 50 30");
        Package expected = new Package("PKG1", 50, 30);

        assertEquals(expected, aPackage);
    }

    @Test
    void shouldFailParsingPackageFromInvalidString() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> packageService.parsePackage("PKG1 50"));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> packageService.parsePackage(""));
        assertThrows(NumberFormatException.class, () -> packageService.parsePackage("PKG1 50 CB"));
    }

    @Test
    void shouldCalculateCostForPackageDelivery() {
        Package pkg = mock(Package.class);
        when(pkg.getWeight()).thenReturn(45);
        when(pkg.getDistance()).thenReturn(15);

        int expectedCost = 525;
        int cost = packageService.costFor(pkg);

        assertEquals(expectedCost, cost);
    }

    @Test
    void shouldCalculateDiscountForPackageDelivery() {
        Package pkg = mock(Package.class);

        packageService.discountFor(pkg);
        verify(offerService, only()).applyOffer(pkg);
    }

    @Test
    void shouldCalculateTotalCost() {
        Package pkg = mock(Package.class);
        when(pkg.getWeight()).thenReturn(60);
        when(pkg.getDistance()).thenReturn(100);
        when(offerService.applyOffer(pkg)).thenReturn(10);

        int totalCost = packageService.totalCost(pkg);
        assertEquals(990, totalCost);
    }
}