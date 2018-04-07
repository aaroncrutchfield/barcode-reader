package com.google.android.gms.samples.vision.barcodereader.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RoomWarnings;

import java.util.List;

/**
 * Created by Aaron Crutchfield on 4/5/2018.
 */

@Dao
public interface PartDao {

    @Insert
    void insertPart(Part part);

    // Returns list of unique partnumbers
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT partnumber FROM part " +
            "GROUP BY partnumber " +
            "ORDER BY partnumber")
    LiveData<List<String>> getPartnumberList();

    // containers=5
    // packQuantity=20,
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT SUM(containers) containers, packQuantity FROM part " +
            "WHERE partnumber = :partnumber " +
            "GROUP BY packQuantity " +
            "ORDER BY packQuantity")
    List<ContainerSum> getContainerSums(String partnumber);

    // packQuantity=20
    // serial=1SFA84165125
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT packQuantity, serial FROM part " +
            "WHERE partnumber = :partnumber")
    LiveData<SerialSummary> getSerialsSummary(String partnumber);

}
