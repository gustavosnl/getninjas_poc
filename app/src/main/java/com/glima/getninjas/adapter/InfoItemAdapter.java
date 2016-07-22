package com.glima.getninjas.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.glima.getninjas.BR;
import com.glima.getninjas.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavo on 22/07/16.
 */
public class InfoItemAdapter extends RecyclerView.Adapter<InfoItemAdapter.ValueHolder> {

    private Context context;
    private List<String> values = new ArrayList<>();

    public InfoItemAdapter(Context context, List<String> values) {
        this.context = context;
        this.values = values;
    }

    @Override
    public ValueHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ValueHolder(DataBindingUtil
                .inflate(LayoutInflater.from(context), R.layout.view_item_info_value, parent, false));
    }

    @Override
    public void onBindViewHolder(ValueHolder holder, int position) {
        holder.getBinding().setVariable(BR.value, values.get(position));
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public static class ValueHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public ValueHolder(ViewDataBinding valueRow) {
            super(valueRow.getRoot());
            binding = valueRow;
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }
}
