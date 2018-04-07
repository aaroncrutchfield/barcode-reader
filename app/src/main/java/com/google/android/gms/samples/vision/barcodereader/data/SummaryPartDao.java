package com.google.android.gms.samples.vision.barcodereader.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Aaron Crutchfield on 4/6/2018.
 */

@Dao
public interface SummaryPartDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertSummaryPart(SummaryPart summaryPart);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void updateSummaryPart(SummaryPart summaryPart);

    @Query("SELECT * FROM summarypart ORDER BY partnumber")
    LiveData<List<SummaryPart>> getSummaryParts();

    @Query("SELECT * FROM summarypart WHERE partnumber = :partnumber")
    SummaryPart getSummaryPartByPartnumber(String partnumber);

}
