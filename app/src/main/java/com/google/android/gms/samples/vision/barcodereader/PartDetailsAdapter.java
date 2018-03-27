package com.google.android.gms.samples.vision.barcodereader;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;

/**
 * Created by ioutd on 3/26/2018.
 */

public class PartDetailsAdapter extends RecyclerView.Adapter<PartDetailsAdapter.PartDetailsViewHolder>{


    private final Context mContext;
    ArrayList<DocumentSnapshot> documents = new ArrayList<>();

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
        DocumentSnapshot document = documents.get(position);
        String quantity =  String.valueOf(document.get("quantity"));
        String serial = document.getId();

        holder.tvDetailQuantity.setText(quantity);
        holder.tvDetailSerial.setText(serial);
    }

    @Override
    public int getItemCount() {
        return documents.size();
    }

    public void switchList(ArrayList<DocumentSnapshot> newArrayList) {
        documents.clear();
        documents.addAll(newArrayList);

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
