package com.glima.getninjas.ui.model;

import android.databinding.BaseObservable;

import com.glima.getninjas.model.Offer;

/**
 * Created by gustavo on 18/07/16.
 */
public class OfferCardViewModel extends BaseObservable {

    private Offer offer;

    public OfferCardViewModel(Offer offer) {
        this.offer = offer;
    }


    public String getTitle() {
        return offer.getTitle();
    }

    public String getUserName() {
        return offer.getRequestingUser().getName();
    }

    public String getDate(){
        return offer.getCreationDate();
    }

    public String getRequestingLocal() {
        return offer.getRequestingAddress().getFormattedAddress();
    }

}
