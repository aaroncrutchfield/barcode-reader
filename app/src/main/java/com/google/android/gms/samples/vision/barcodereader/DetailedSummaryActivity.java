package com.google.android.gms.samples.vision.barcodereader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailedSummaryActivity extends AppCompatActivity {

    @BindView(R.id.rv_detailed_summary)
    RecyclerView rvDetailedSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_summary);
        ButterKnife.bind(this);


    }
}
