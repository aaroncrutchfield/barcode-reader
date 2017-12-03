package com.google.android.gms.samples.vision.barcodereader;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ioutd on 12/2/2017.
 */

public class SummaryRecyclerViewAdapter extends RecyclerView.Adapter<SummaryRecyclerViewAdapter.SummaryViewHolder> {

    @Override
    public SummaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(SummaryViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class SummaryViewHolder extends RecyclerView.ViewHolder{

        public SummaryViewHolder(View itemView) {
            super(itemView);
        }
    }

}
