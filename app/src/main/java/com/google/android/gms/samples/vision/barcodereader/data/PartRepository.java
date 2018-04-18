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

    public LiveData<List<String>> getPartnumberList() {
        return partDao.getPartnumberList();
    }

    public List<ContainerSum> getContainerSums(String partnumber) {
        return partDao.getContainerSums(partnumber);
    }

    public LiveData<List<SerialSummary>> getSerialsSummary(String partnumber) {
        return partDao.getSerialsSummary(partnumber);
    }

    public void insertPart(Part part) {
        partDao.insertPart(part);
    }

}
