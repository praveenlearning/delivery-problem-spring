package com.example.everestproblem1spring.service;

import com.example.everestproblem1spring.model.Offer;
import com.example.everestproblem1spring.model.Package;
import org.springframework.stereotype.Service;

@Service
public class OfferService {

    public int applyOffer(Package pkg) {
        Offer offer = get(pkg.getOfferCode());
        return offer.apply(pkg);
    }

    public Offer get(String offerCode) {
        Offer offer = Offer.NONE;
        if (offerCode == null)
            return offer;
        try {
            offer = Enum.valueOf(Offer.class, offerCode);
        } catch (IllegalArgumentException ignored) {
        }
        return offer;
    }
}
