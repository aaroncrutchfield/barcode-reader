package com.google.android.gms.samples.vision.barcodereader;


import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.android.gms.samples.vision.barcodereader.data.InventoryDatabase;
import com.google.android.gms.samples.vision.barcodereader.data.PartRepository;
import com.google.android.gms.samples.vision.barcodereader.data.PartViewModel;
import com.google.android.gms.samples.vision.barcodereader.data.SerialSummary;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PartDetailsActivity extends AppCompatActivity {


    @BindView(R.id.rv_part_details)
    RecyclerView rvPartDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_details);
        ButterKnife.bind(this);




        final PartDetailsAdapter adapter = new PartDetailsAdapter(this);
        rvPartDetails.setAdapter(adapter);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvPartDetails.setLayoutManager(layoutManager);


        Intent intent = getIntent();
        String part = intent.getStringExtra("part");
        getSupportActionBar().setTitle(part);

        // TODO: 4/18/2018 Observe changes to the database and update adapter
        InventoryDatabase db = InventoryDatabase.getAppDatabase(this);
        PartRepository partRepository = new PartRepository(db.partDao());
        PartViewModel partViewModel = new PartViewModel(partRepository);

        partViewModel.getSerialsSummary(part).observe(this, new Observer<List<SerialSummary>>() {
            @Override
            public void onChanged(@Nullable List<SerialSummary> serialSummaries) {
                adapter.switchList(serialSummaries);
            }
        });
    }
}