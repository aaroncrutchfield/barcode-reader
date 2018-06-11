package com.google.android.gms.samples.vision.barcodereader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.samples.vision.barcodereader.data.InventoryDatabase;
import com.google.android.gms.samples.vision.barcodereader.data.Part;
import com.google.android.gms.samples.vision.barcodereader.data.PartDao;
import com.google.android.gms.samples.vision.barcodereader.data.PartRepository;
import com.google.android.gms.samples.vision.barcodereader.data.PartViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfirmDialogActivity extends AppCompatActivity {

    @BindView(R.id.btn_delete)
    Button btnDelete;

    @BindView(R.id.btn_update)
    Button btnUpdate;

    @BindView(R.id.tv_message)
    TextView tvMessage;

    PartViewModel partViewModel;
    Part oldPart;
    Part newPart;
    Intent mainActivityIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_dialog);
        ButterKnife.bind(this);

        mainActivityIntent = new Intent(this, MainActivity.class);

        Intent intent = getIntent();
        oldPart = intent.getParcelableExtra("oldPart");
        newPart = intent.getParcelableExtra("newPart");

        String message = newPart.getSerial() + " has already been scanned";
        tvMessage.setText(message);

        InventoryDatabase database = InventoryDatabase.getAppDatabase(this);
        PartRepository partRepository = new PartRepository(database.partDao());

        partViewModel = new PartViewModel(partRepository);
    }

    @OnClick(R.id.btn_delete)
    void onDeleteClicked() {
        partViewModel.deletePart(oldPart);
        finish();
//        startActivity(mainActivityIntent);
    }

    @OnClick(R.id.btn_update)
    void onUpdateClicked() {
        partViewModel.updatePart(newPart);
        finish();
//        startActivity(mainActivityIntent);
    }
}
