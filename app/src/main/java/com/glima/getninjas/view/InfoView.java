package com.glima.getninjas.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.glima.getninjas.R;
import com.glima.getninjas.adapter.InfoItemAdapter;
import com.glima.getninjas.databinding.ViewInfoItemBinding;
import com.glima.getninjas.view.model.InfoItemViewModel;

/**
 * Created by gustavo on 22/07/16.
 */
public class InfoView extends LinearLayout {

    RecyclerView recyclerView;
    ViewInfoItemBinding viewDataBinding;

    public InfoView(Context context) {
        super(context);
        viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_info_item, this, true);
        init();
    }

    private void init() {
        recyclerView = viewDataBinding.values;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), VERTICAL, false));
    }

    public void attachViewModel(InfoItemViewModel viewModel) {
        viewDataBinding.setInfo(viewModel);
        recyclerView.setAdapter(new InfoItemAdapter(getContext(),viewModel.getValues()));
    }
}
