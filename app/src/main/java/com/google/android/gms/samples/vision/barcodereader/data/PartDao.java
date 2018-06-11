package com.google.android.gms.samples.vision.barcodereader.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RoomWarnings;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Aaron Crutchfield on 4/5/2018.
 */

@Dao
public interface PartDao {

    @Insert
    void insertPart(Part part);

    @Update
    void updatePart(Part part);

    @Delete
    void deletePart(Part part);

    @Query("SELECT * FROM part " +
            "WHERE serial = :serial")
    Part getPartBySerial(String serial);

    @Query("SELECT partnumber, SUM(packQuantity) AS total FROM part " +
            "GROUP BY partnumber " +
            "ORDER BY partnumber")
    LiveData<List<SummaryPart>> getLiveSummaryParts();

    @Query("SELECT partnumber, SUM(packQuantity) AS total FROM part " +
            "GROUP BY partnumber " +
            "ORDER BY partnumber")
    List<SummaryPart> getSummaryParts();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT partnumber FROM part " +
            "GROUP BY partnumber " +
            "ORDER BY partnumber")
    List<String> getPartsList();

    // packQuantity=20
    // serial=1SFA84165125
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT packQuantity, serial FROM part " +
            "WHERE partnumber = :partnumber " +
            "ORDER BY serial")
    LiveData<List<SerialSummary>> getLiveSerialsList(String partnumber);

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT packQuantity, serial FROM part " +
            "WHERE partnumber = :partnumber " +
            "ORDER BY serial")
    List<SerialSummary> getSerialsList(String partnumber);
}
