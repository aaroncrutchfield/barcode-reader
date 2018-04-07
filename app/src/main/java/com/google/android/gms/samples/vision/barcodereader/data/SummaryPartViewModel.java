package com.google.android.gms.samples.vision.barcodereader.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

/**
 * Created by Aaron Crutchfield on 4/6/2018.
 */

public class SummaryPartViewModel extends ViewModel {
    SummaryPartRepository summaryPartRepository;

    public SummaryPartViewModel(SummaryPartRepository summaryPartRepository) {
        this.summaryPartRepository = summaryPartRepository;
    }

    public LiveData<List<SummaryPart>> getSummaryParts() {
        return summaryPartRepository.getSummaryParts();
    }

    public void upsertSummaryPart(SummaryPart summaryPart) {
        summaryPartRepository.upsertSummaryPart(summaryPart);
    }

    public long insertSummaryPart(SummaryPart summaryPart) {
        return summaryPartRepository.insertSummaryPart(summaryPart);
    }
}
