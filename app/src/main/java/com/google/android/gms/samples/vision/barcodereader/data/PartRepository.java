package com.google.android.gms.samples.vision.barcodereader.data;

import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * Created by Aaron Crutchfield on 4/5/2018.
 */

public class PartRepository {

    private final PartDao partDao;

    public PartRepository(PartDao partDao) {
        this.partDao = partDao;
    }

    public LiveData<List<SerialSummary>> getSerialsSummary(String partnumber) {
        return partDao.getLiveSerialsList(partnumber);
    }

    public Part getPartBySerial(String serial) {
        return partDao.getPartBySerial(serial);
    }

    public LiveData<List<SummaryPart>> getLiveSummaryParts() {
        return partDao.getLiveSummaryParts();
    }


    public void insertPart(Part part) {
        partDao.insertPart(part);
    }

    public void updatePart(Part part) {
        partDao.updatePart(part);
    }

    public void deletePart(Part part) {
        partDao.deletePart(part);
    }

    /**
     * returns summary of totals for all parts in the database
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        List<String> partnumbers = partDao.getPartsList();
        List<SummaryPart> totalsList = partDao.getSummaryParts();
        builder.append("----------------TOTALS----------------\n\n");
        for (SummaryPart totalSummary: totalsList) {
            builder.append(totalSummary.partnumber + "\n" +
                    totalSummary.total + "pcs" +
                    "\n\n"
            );
        }

        builder.append("\n");
        builder.append("----------------SERIALS----------------\n\n");

        for (String partnumber: partnumbers) {
            List<SerialSummary> serialsList = partDao.getSerialsList(partnumber);
            builder.append("\n-----------------------------------------\n");
            builder.append(partnumber + "\n");
            builder.append("-----------------------------------------\n");
            for (SerialSummary serialSummary : serialsList) {
                builder.append(serialSummary.serial + "     " + serialSummary.packQuantity + "\n");
            }
        }
        return builder.toString();
    }

}
