package com.glima.getninjas.fragment;

import com.glima.getninjas.R;

/**
 * Created by gustavo on 16/07/16.
 */
public class LeadFragment extends BaseFragment {

    public LeadFragment() {
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_leads;
    }

    @Override
    public int getTitle() {
        return R.string.title_fragment_leads;
    }
}
