package com.glima.getninjas.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.glima.getninjas.fragment.TitledFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavo on 16/07/16.
 */
public class TitledFragmentAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private final List<Fragment> mFragmentList = new ArrayList<>();

    public TitledFragmentAdapter(Context context, FragmentManager manager, List<Fragment> fragments) {
        super(manager);
        mContext = context;
        mFragmentList.addAll(fragments);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getString(((TitledFragment) mFragmentList.get(position)).getTitle());
    }
}


