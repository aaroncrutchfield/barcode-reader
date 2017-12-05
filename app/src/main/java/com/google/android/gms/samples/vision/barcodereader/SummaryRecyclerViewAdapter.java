package com.google.android.gms.samples.vision.barcodereader;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;

/**
 * Created by ioutd on 12/2/2017.
 */

public class SummaryRecyclerViewAdapter extends RecyclerView.Adapter<SummaryRecyclerViewAdapter.SummaryViewHolder> {


    private Cursor mCursor = null;

    @Override
    public SummaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.summary_item, parent, false);

        return new SummaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SummaryViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position))
            return;

        holder.partnumber.setText(
                mCursor.getString(mCursor.getColumnIndex("partnumber")));

        holder.totalQuantity.setText(
                String.valueOf(mCursor.getInt(1)));
    }

    @Override
    public int getItemCount() {
        if (mCursor == null) return 0;
        return mCursor.getCount();
    }

    public void switchCursor(Cursor newCursor) {
        //Close the previous mCursor
        if (mCursor != null) mCursor.close();
        mCursor = newCursor;
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
        }
    }

}
