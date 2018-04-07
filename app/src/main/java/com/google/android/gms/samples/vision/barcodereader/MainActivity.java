/*
 * Copyright (C) The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.gms.samples.vision.barcodereader;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.samples.vision.barcodereader.data.InventoryDatabase;
import com.google.android.gms.samples.vision.barcodereader.data.SummaryPart;
import com.google.android.gms.samples.vision.barcodereader.data.SummaryPartRepository;
import com.google.android.gms.samples.vision.barcodereader.data.SummaryPartViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Main activity demonstrating how to pass extra parameters to an activity that
 * reads barcodes.
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.auto_focus)
    Switch autoFocus;
    @BindView(R.id.use_flash)
    Switch useFlash;
    @BindView(R.id.btn_scan)
    Button btnScan;
    @BindView(R.id.btn_clear_db)
    Button btnClearDb;
    @BindView(R.id.rv_summary)
    RecyclerView rvSummary;

    @BindView(R.id.tv_partnumber)
    TextView tvPartnumber;
    @BindView(R.id.tv_quantitiy)
    TextView tvQuantity;
    @BindView(R.id.tv_serial)
    TextView tvSerial;

    private static final String TAG = "BarcodeMain";
    private SummaryRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //setup the recyclerview
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        adapter = new SummaryRecyclerViewAdapter(this);

        rvSummary.setLayoutManager(layoutManager);
        rvSummary.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //For each partnumber, sum up their quantities and send that information to the recyclerview
        queryDatabase();
    }

    private void queryDatabase() {
        // Get an instance of the database
        InventoryDatabase database = InventoryDatabase.getAppDatabase(this);

        // Instantiate the repository and viewModel
        SummaryPartRepository repository = new SummaryPartRepository(database.summaryPartDao());
        final SummaryPartViewModel viewModel = new SummaryPartViewModel(repository);

        viewModel.getSummaryParts().observe(this, new Observer<List<SummaryPart>>() {
            @Override
            public void onChanged(@Nullable List<SummaryPart> summaryParts) {
                adapter.switchList(summaryParts);
                Log.d(TAG, "onChanged: " + summaryParts.toString());
            }
        });
    }

    @OnClick(R.id.btn_scan)
    public void onViewClicked() {
        Intent intent = new Intent(this, BarcodeCaptureActivity.class);
        intent.putExtra(BarcodeCaptureActivity.AutoFocus, autoFocus.isChecked());   // TODO: 12/1/2017 onClick() - set AutoFocus to always be true
        intent.putExtra(BarcodeCaptureActivity.UseFlash, useFlash.isChecked());

        startActivityForResult(intent, 1);
    }

    @OnClick(R.id.btn_clear_db)
    public void onClearClicked() {
        // TODO: 4/6/2018 onClearClicked() - Clear the database
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 1) {
            String partnumber = data.getStringExtra("partnumber").substring(1);
            String quantity = formatQuantity(data.getStringExtra("quantity").substring(1));
            String serial = data.getStringExtra("serial");

            tvPartnumber.setText(partnumber);
            tvQuantity.setText(quantity);
            tvSerial.setText(serial);
        }
        if (resultCode == 0) {
            // TODO: 4/6/2018 onActivityResult() - currently shows Toast everytime
            Toast.makeText(this, "Barcode already scanned", Toast.LENGTH_SHORT).show();
        }
    }

    private String formatQuantity(String quantity) {
        // Convert quantity to int and back to string to remove leading zeros
        int quantityInt = Integer.valueOf(quantity);
        return String.valueOf(quantityInt);
    }
}
