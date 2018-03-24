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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Switch;

import com.google.android.gms.samples.vision.barcodereader.data.Part;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Main activity demonstrating how to pass extra parameters to an activity that
 * reads barcodes.
 */
public class MainActivity extends Activity {

    @BindView(R.id.auto_focus)
    Switch autoFocus;
    @BindView(R.id.use_flash)
    Switch useFlash;
    @BindView(R.id.fab_scan_barcode)
    FloatingActionButton fabScanBarcode;
    @BindView(R.id.rv_summary)
    RecyclerView rvSummary;

    private static final String TAG = "BarcodeMain";
    private SummaryRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //setup the recyclerview
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        adapter = new SummaryRecyclerViewAdapter();

        rvSummary.setLayoutManager(layoutManager);
        rvSummary.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //For each partnumber, sum up their quantities and send that information to the recyclerview

        //Get an instance of the Firebase DB
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //Create an arraylist of parts
        final ArrayList<Part> partArrayList = new ArrayList<>();

        //Add a SnapShotListener to the collection where the parts reside
        db.collection("COL_PICKLISTS").document("DOC_FAC_20171205_1902").collection("COL_PARTS")
                .addSnapshotListener(this, new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                        if (e != null){
                            Log.d(TAG, "onEvent: " + e.getMessage());
                        }

                        //create a set of unique numbers
                        Set<String> uniquePartList = new HashSet<>();
                        for (DocumentSnapshot document :
                                documentSnapshots) {
                            uniquePartList.add((String) document.get("partnumber"));
                        }

                        ArrayList<String> partlist = new ArrayList<>(uniquePartList);

                        //Sum the values of matching partnumbers
                        for (int i = 0; i < partlist.size(); i++) {
                            Part part = null;
                            int total = 0;
                            for (DocumentSnapshot document :
                                    documentSnapshots) {
                                String tempPart = (String) document.get("partnumber");

                                if (partlist.get(i).equals(tempPart)) {
                                    total += (long) document.get("quantity");
                                }
                            }

                            part = new Part.Builder()
                                    .partnumber(partlist.get(i))
                                    .quantity(total)
                                    .build();

                            //Add the values to the arraylist
                            partArrayList.add(part);
                            partArrayList.sort(new Comparator<Part>() {
                                @Override
                                public int compare(Part part, Part part2) {
                                    return part.getPartnumber().compareTo(part2.getPartnumber());
                                }
                            });
                        }
                        adapter.switchCursor(partArrayList);
                    }
                });
    }

    @OnClick(R.id.fab_scan_barcode)
    public void onViewClicked() {
        Intent intent = new Intent(this, BarcodeCaptureActivity.class);
        intent.putExtra(BarcodeCaptureActivity.AutoFocus, autoFocus.isChecked());   // TODO: 12/1/2017 onClick() - set AutoFocus to always be true
        intent.putExtra(BarcodeCaptureActivity.UseFlash, useFlash.isChecked());

        startActivity(intent);
    }
}
