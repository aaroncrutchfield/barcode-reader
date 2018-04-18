package com.google.android.gms.samples.vision.barcodereader.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.database.sqlite.SQLiteConstraintException;

import java.util.List;

/**
 * Created by Aaron Crutchfield on 4/6/2018.
 */

public class PartViewModel extends ViewModel {

    PartRepository partRepository;
    List<ContainerSum> containerSums;

    public PartViewModel(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    public LiveData<List<String>> getPartnumberList() {
        return partRepository.getPartnumberList();
    }

    public LiveData<List<SerialSummary>> getSerialsSummary(String partnumber) {
        return partRepository.getSerialsSummary(partnumber);
    }

    public void insertPart(final Part part) throws SQLiteConstraintException {
        new Runnable() {
            @Override
            public void run() {
                partRepository.insertPart(part);
                SummaryPart summaryPart = new SummaryPart();
                summaryPart.setPartnumber(part.getPartnumber());
                summaryPart.setTotal(part.getPackQuantity());
            }
        }.run();
    }
}
