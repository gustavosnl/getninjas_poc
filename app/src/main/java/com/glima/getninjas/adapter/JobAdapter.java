package com.glima.getninjas.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.glima.getninjas.R;
import com.glima.getninjas.databinding.ViewCardOfferBinding;
import com.glima.getninjas.model.Job;
import com.glima.getninjas.view.model.JobOfferCardViewModel;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by gustavo on 18/07/16.
 */
public class JobAdapter extends RecyclerView.Adapter<JobAdapter.OfferViewHolder> {

    private List<Job> jobs = new ArrayList<>();
    private Context context;
    private PublishSubject<JobOfferCardViewModel> publishSubject = PublishSubject.create();

    public JobAdapter(Context context) {
        this.context = context;
    }

    @Override
    public OfferViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OfferViewHolder(DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_card_offer, parent, false));
    }

    @Override
    public void onBindViewHolder(OfferViewHolder holder, int position) {
        holder.attachOffer(jobs.get(position));
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public void reload(List<Job> jobs) {
        this.jobs.addAll(jobs);
        notifyDataSetChanged();
    }

    public Observable<JobOfferCardViewModel> getObservable() {
        return publishSubject.asObservable();
    }

    class OfferViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private JobOfferCardViewModel viewModel;
        private ViewDataBinding viewDataBinding;

        public OfferViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            this.viewDataBinding = viewDataBinding;
            itemView.setOnClickListener(this);
        }

        public void attachOffer(Job job) {
            viewModel = new JobOfferCardViewModel(job);
            ((ViewCardOfferBinding) viewDataBinding).setJob(viewModel);
        }

        @Override
        public void onClick(View view) {
            publishSubject.onNext(viewModel);
        }
    }
}
