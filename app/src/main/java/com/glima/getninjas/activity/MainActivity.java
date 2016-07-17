package com.glima.getninjas.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.glima.getninjas.R;
import com.glima.getninjas.adapter.TitledFragmentAdapter;
import com.glima.getninjas.databinding.ActivityMainBinding;
import com.glima.getninjas.fragment.LeadListFragment;
import com.glima.getninjas.fragment.OfferListFragment;

import java.util.Arrays;

public class MainActivity extends BaseActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void init() {
        setupViewPager();
        setupTabLayout();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupToolbar() {
        toolbar = ((ActivityMainBinding) viewDataBinding).includeToolbar.toolbar;
        setSupportActionBar(toolbar);
    }

    private void setupViewPager() {
        viewPager = ((ActivityMainBinding) viewDataBinding).viewpager;
        TitledFragmentAdapter adapter = new TitledFragmentAdapter(
                this,
                getSupportFragmentManager(),
                Arrays.<Fragment>asList(new OfferListFragment(), new LeadListFragment()));
        viewPager.setAdapter(adapter);
    }

    private void setupTabLayout() {
        tabLayout = ((ActivityMainBinding) viewDataBinding).includeToolbar.tab;
        tabLayout.setupWithViewPager(viewPager);
    }

}
