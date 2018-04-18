package com.google.android.gms.samples.vision.barcodereader;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.samples.vision.barcodereader.data.SummaryPart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ioutd on 12/2/2017.
 */

public class SummaryRecyclerViewAdapter extends RecyclerView.Adapter<SummaryRecyclerViewAdapter.SummaryViewHolder> {


    private final Context mContext;
    private List<SummaryPart> mPartArrayList = new ArrayList<>();

    public SummaryRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    @Override
    public SummaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.summary_item, parent, false);

        return new SummaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SummaryViewHolder holder, int position) {
        SummaryPart summaryPart = mPartArrayList.get(position);

        holder.partnumber.setText(summaryPart.getPartnumber());

        holder.totalQuantity.setText(String.valueOf(summaryPart.getTotal()));
    }

    @Override
    public int getItemCount() {
        return mPartArrayList.size();
    }

    public void switchList(List<SummaryPart> newList) {
        //Close the previous mPartArrayList
        mPartArrayList.clear();
        mPartArrayList.addAll(newList);

        //Forces the Recyclerview to refresh and reflect the new data
        notifyDataSetChanged();
    }

    class SummaryViewHolder extends RecyclerView.ViewHolder{
//        @BindView(R.id.tv_partnumber)
        TextView partnumber;
//        @BindView(R.id.tv_total_quantity)
        TextView totalQuantity;
//        @BindView(R.id.iv_part_image)
        ImageView partImage;

        public SummaryViewHolder(View itemView) {
            super(itemView);
            partnumber = itemView.findViewById(R.id.tv_partnumber);
            totalQuantity = itemView.findViewById(R.id.tv_total_quantity);
            partImage = itemView.findViewById(R.id.iv_part_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, PartDetailsActivity.class);
                    String partString = partnumber.getText().toString();
                    intent.putExtra("part", partString);

                    mContext.startActivity(intent);
                }
            });
        }
    }

}
