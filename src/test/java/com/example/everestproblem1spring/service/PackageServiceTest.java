package com.example.everestproblem1spring.service;

import com.example.everestproblem1spring.model.Package;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PackageServiceTest {

    private final PackageService packageService = new PackageService();

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
}