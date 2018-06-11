package com.google.android.gms.samples.vision.barcodereader;


import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


import com.google.android.gms.samples.vision.barcodereader.data.InventoryDatabase;
import com.google.android.gms.samples.vision.barcodereader.data.Part;
import com.google.android.gms.samples.vision.barcodereader.data.PartRepository;
import com.google.android.gms.samples.vision.barcodereader.data.PartViewModel;
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    promptForNewQuantity();
                }
            });
        }

        private void promptForNewQuantity() {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

            final EditText editText = new EditText(mContext);
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);

            InventoryDatabase database = InventoryDatabase.getAppDatabase(mContext);
            PartRepository partRepository = new PartRepository(database.partDao());
            final PartViewModel partViewModel = new PartViewModel(partRepository);

            builder.setTitle("Enter a new quantity")
                    .setView(editText)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            getQuantityInput(editText, partViewModel);
                        }
                    })
                    .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .setOnKeyListener(new DialogInterface.OnKeyListener() {
                        @Override
                        public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
                            if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) &&
                                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                                getQuantityInput(editText, partViewModel);
                                dialogInterface.dismiss();
                                return true;
                            }
                            return false;
                        }
                    })
                    .create()
                    .show();
        }

        private void getQuantityInput(EditText editText, PartViewModel partViewModel) {
            int quantity = Integer.valueOf(editText.getText().toString());
            String serial = tvDetailSerial.getText().toString();

            Part part = partViewModel.getPartBySerial(serial);
            part.setPackQuantity(quantity);
            partViewModel.updatePart(part);
        }
    }
}