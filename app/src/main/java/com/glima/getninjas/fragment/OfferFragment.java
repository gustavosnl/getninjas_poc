package com.glima.getninjas.fragment;

import android.os.Bundle;

import com.glima.getninjas.R;

/**
 * Created by gustavo on 16/07/16.
 */
public class OfferFragment extends BaseFragment {


    @Override
    protected int getLayout() {
        return R.layout.fragment_offers;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getTitle() {
        return R.string.title_fragment_offers;

    }
}
