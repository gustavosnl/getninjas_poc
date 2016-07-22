package com.glima.getninjas.view.model;

import android.databinding.BaseObservable;

import com.glima.getninjas.model.Info;

import java.util.List;

/**
 * Created by gustavo on 21/07/16.
 */
public class InfoItemViewModel extends BaseObservable {

    private Info info;

    public InfoItemViewModel(Info info) {
        this.info = info;
    }

    public String getLabel() {
        return info.getLabel();
    }

    public List<String> getValues() {
        return info.getContent();
    }
}
