package com.glima.getninjas.model;

/**
 * Created by gustavo on 16/07/16.
 */
public class Lead extends Offer {

    public Lead(Offer offer) {
        super(offer.getId(),
                offer.getTitle(),
                offer.getIsRead(),
                offer.getCreationDate(),
                offer.getRequestingUser(),
                offer.getRequestingAddress());
    }


}
