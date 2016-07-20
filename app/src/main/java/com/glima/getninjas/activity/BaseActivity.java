package com.glima.getninjas.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * Created by gustavo on 05/07/16.
 */
public abstract class BaseActivity extends RxAppCompatActivity {
    protected Toolbar toolbar;
    protected ViewDataBinding viewDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, getLayout());
        setupToolbar();
        init();
    }

    protected abstract void init();

    protected abstract void setupToolbar();

    public abstract int getLayout();
}
