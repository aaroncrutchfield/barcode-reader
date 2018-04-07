package com.google.android.gms.samples.vision.barcodereader.data;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import java.util.List;

/**
 * Created by Aaron Crutchfield on 4/6/2018.
 */

public class SummaryPartRepository {
    private final SummaryPartDao summaryPartDao;

    public SummaryPartRepository(SummaryPartDao summaryPartDao) {
        this.summaryPartDao = summaryPartDao;
    }

    public LiveData<List<SummaryPart>> getSummaryParts() {
        return summaryPartDao.getSummaryParts();
    }

    public long insertSummaryPart(SummaryPart summaryPart) {
        return summaryPartDao.insertSummaryPart(summaryPart);
    }

    public void updateSummaryPart(SummaryPart summaryPart) {
        summaryPartDao.updateSummaryPart(summaryPart);
    }

    // Upsert method
    // https://stackoverflow.com/questions/45677230/android-room-persistence-library-upsert?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
    public void upsertSummaryPart(SummaryPart currentSummaryPart) {
        long id = insertSummaryPart(currentSummaryPart);
        Log.d("SummaryPartRepository", "upsertSummaryPart: id=" + id);
        if (id == -1) {
            int currentTotal = currentSummaryPart.getTotal();
            String currentPartnumber = currentSummaryPart.getPartnumber();

            SummaryPart oldSummaryPart = summaryPartDao.getSummaryPartByPartnumber(currentPartnumber);
            int oldTotal = oldSummaryPart.getTotal();

            currentSummaryPart.setTotal(oldTotal + currentTotal);
            updateSummaryPart(currentSummaryPart);
        }
    }
}
