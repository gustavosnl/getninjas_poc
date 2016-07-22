package com.glima.getninjas.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.glima.getninjas.model.Info;
import com.glima.getninjas.view.InfoView;
import com.glima.getninjas.view.model.InfoItemViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavo on 22/07/16.
 */
public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder> {

    private List<Info> infoList = new ArrayList<>();
    private Context context;

    public InfoAdapter(Context context, List<Info> infoList) {
        this.context = context;
        this.infoList.addAll(infoList);
    }

    @Override
    public InfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InfoViewHolder(new InfoView(context));
    }

    @Override
    public void onBindViewHolder(InfoViewHolder holder, int position) {
        holder.attachData(infoList.get(position));
    }

    @Override
    public int getItemCount() {
        return infoList.size();
    }

    public class InfoViewHolder extends RecyclerView.ViewHolder {

        public InfoViewHolder(InfoView itemView) {
            super(itemView);
        }

        public void attachData(Info info) {
            ((InfoView) itemView).attachViewModel(new InfoItemViewModel(info));
        }
    }
}
