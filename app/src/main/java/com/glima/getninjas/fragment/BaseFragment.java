package com.glima.getninjas.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by gustavo on 16/07/16.
 */
public abstract class BaseFragment extends Fragment implements TitledFragment {

    protected ViewDataBinding viewDataBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayout(), container, false);
        return viewDataBinding.getRoot();
    }

    protected abstract int getLayout();
}
