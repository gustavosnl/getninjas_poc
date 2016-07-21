package com.glima.getninjas.view.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by gustavo on 06/07/16.
 */
public class MargingDecoration extends RecyclerView.ItemDecoration {

    private final int marging;

    public MargingDecoration(int marging) {
        this.marging = marging;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = marging;
        outRect.left = marging;
        outRect.right = marging;
        outRect.top = marging;
    }
}
