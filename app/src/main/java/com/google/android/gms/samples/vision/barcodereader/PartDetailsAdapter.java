package com.google.android.gms.samples.vision.barcodereader;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.google.android.gms.samples.vision.barcodereader.data.SerialSummary;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ioutd on 3/26/2018.
 */


public class PartDetailsAdapter extends RecyclerView.Adapter<PartDetailsAdapter.PartDetailsViewHolder>{




    private final Context mContext;
    List<SerialSummary> serialsSummaryList = new ArrayList<>();


    public PartDetailsAdapter(Context context) {
        mContext = context;
    }


    @Override
    public PartDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.part_detail_item, parent, false);
        return new PartDetailsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(PartDetailsViewHolder holder, int position) {
        SerialSummary serialSummary = serialsSummaryList.get(position);
        String quantity =  String.valueOf(serialSummary.packQuantity);
        String serial = serialSummary.serial;


        holder.tvDetailQuantity.setText(quantity);
        holder.tvDetailSerial.setText(serial);
    }


    @Override
    public int getItemCount() {
        return serialsSummaryList.size();
    }


    public void switchList(List<SerialSummary> newList) {
        serialsSummaryList.clear();
        serialsSummaryList.addAll(newList);


        notifyDataSetChanged();
    }


    class PartDetailsViewHolder extends RecyclerView.ViewHolder {


        TextView tvDetailQuantity;
        TextView tvDetailSerial;


        public PartDetailsViewHolder(View itemView) {
            super(itemView);
            tvDetailQuantity = itemView.findViewById(R.id.tv_detail_quantity);
            tvDetailSerial = itemView.findViewById(R.id.tv_detail_serial);
        }
    }
}