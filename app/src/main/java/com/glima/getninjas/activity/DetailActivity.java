package com.glima.getninjas.activity;

import com.glima.getninjas.R;
import com.glima.getninjas.databinding.ActivityDetailBinding;

/**
 * Created by gustavo on 19/07/16.
 */
public class DetailActivity extends BaseActivity {

    @Override
    protected void init() {

    }

    @Override
    protected void setupToolbar() {
        toolbar = ((ActivityDetailBinding) viewDataBinding).includeToolbar.toolbar;
        setSupportActionBar(toolbar);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_detail;
    }
}
