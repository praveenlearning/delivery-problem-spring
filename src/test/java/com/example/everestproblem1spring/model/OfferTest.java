package com.example.everestproblem1spring.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OfferTest {


    @Test
    void shouldReturnDiscountOnAPackage() {
        Offer offer = Offer.OFR001;
        Package pkgMock = mock(Package.class);

        when(pkgMock.getDistance()).thenReturn(70);
        when(pkgMock.getWeight()).thenReturn(200);

        int expectedDiscount = 10;
        int discount = offer.apply(pkgMock);

        assertEquals(expectedDiscount, discount);
    }

    @Test
    void shouldReturnZeroDiscountIfOfferIsInvalid() {
        Offer offer = Offer.OFR001;
        Package pkgMock = mock(Package.class);

        when(pkgMock.getDistance()).thenReturn(70);
        when(pkgMock.getWeight()).thenReturn(210);

        int expectedDiscount = 0;
        int discount = offer.apply(pkgMock);

        assertEquals(expectedDiscount, discount);
    }
}