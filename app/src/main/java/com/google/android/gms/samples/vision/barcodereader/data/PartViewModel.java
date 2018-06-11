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

    public LiveData<List<SerialSummary>> getSerialsSummary(String partnumber) {
        return partRepository.getSerialsSummary(partnumber);
    }

    public Part getPartBySerial(String serial) {
        return partRepository.getPartBySerial(serial);
    }

    public LiveData<List<SummaryPart>> getLiveSummaryParts() {
        return partRepository.getLiveSummaryParts();
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

    public void updatePart(final Part part) throws SQLiteConstraintException {
        new Runnable() {
            @Override
            public void run() {
                partRepository.updatePart(part);
                SummaryPart summaryPart = new SummaryPart();
                summaryPart.setPartnumber(part.getPartnumber());
                summaryPart.setTotal(part.getPackQuantity());
            }
        }.run();
    }

    public void deletePart(Part part) {
        partRepository.deletePart(part);
    }

    public String toString() {
        // TODO: 4/18/2018 toString - Should the summaryRepo be accessed here instead of the DAO
        final StringBuilder builder = new StringBuilder();
        new Runnable() {
            @Override
            public void run() {
                builder.append(partRepository.toString());
            }
        }.run();
        return builder.toString();
    }
}
