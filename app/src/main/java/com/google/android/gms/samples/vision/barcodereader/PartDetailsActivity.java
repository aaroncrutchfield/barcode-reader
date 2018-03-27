package com.google.android.gms.samples.vision.barcodereader;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

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

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        CollectionReference partsRef = db.collection("COL_PICKLISTS")
                .document("DOC_FAC_20171205_1902").collection("COL_PARTS");
        Query query = partsRef.whereEqualTo("partnumber", part);

        final ArrayList<DocumentSnapshot> documents = new ArrayList<>();

        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document: task.getResult()){
                                documents.add(document);
                            }
                            adapter.switchList(documents);
                        } else {
                            Toast.makeText(PartDetailsActivity.this, "Failed to get details", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
