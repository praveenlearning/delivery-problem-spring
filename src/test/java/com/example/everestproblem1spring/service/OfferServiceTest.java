package com.example.everestproblem1spring.service;

import com.example.everestproblem1spring.model.Offer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OfferServiceTest {

    private final OfferService offerService = new OfferService();

    @Test
    void shouldGetOfferWithOfferCode() {
        Offer ofr001 = offerService.get("OFR001");

        Offer expected = Offer.OFR001;

        assertEquals(expected, ofr001);
    }

    @Test
    void shouldGetNoneWithInvalidOfferCode() {
        Offer offer = offerService.get("OFR009");

        Offer expected = Offer.NONE;

        assertEquals(expected, offer);
    }
}