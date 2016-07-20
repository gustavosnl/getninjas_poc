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
import com.glima.getninjas.model.Offer;
import com.glima.getninjas.ui.model.OfferCardViewModel;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by gustavo on 18/07/16.
 */
public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.OfferViewHolder> {

    private List<Offer> offers = new ArrayList<>();
    private Context context;
    private PublishSubject<OfferCardViewModel> publishSubject = PublishSubject.create();

    public OfferAdapter(Context context) {
        this.context = context;
    }

    @Override
    public OfferViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OfferViewHolder(DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_card_offer, parent, false));
    }

    @Override
    public void onBindViewHolder(OfferViewHolder holder, int position) {
        holder.attachOffer(offers.get(position));
    }

    @Override
    public int getItemCount() {
        return offers.size();
    }

    public void reload(List<Offer> offers) {
        this.offers.addAll(offers);
        notifyDataSetChanged();
    }

    public Observable<OfferCardViewModel> getObservable() {
        return publishSubject.asObservable();
    }

    class OfferViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OfferCardViewModel viewModel;
        private ViewDataBinding viewDataBinding;

        public OfferViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            this.viewDataBinding = viewDataBinding;
            itemView.setOnClickListener(this);
        }

        public void attachOffer(Offer offer) {
            viewModel = new OfferCardViewModel(offer);
            ((ViewCardOfferBinding) viewDataBinding).setOffer(viewModel);
        }

        @Override
        public void onClick(View view) {
            publishSubject.onNext(viewModel);
        }
    }
}
