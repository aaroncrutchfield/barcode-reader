package com.google.android.gms.samples.vision.barcodereader.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ioutd on 12/2/2017.
 */

@Dao
public interface PartDao {
    @Query("SELECT * FROM part")
    List<Part> getAll();

    @Query("SELECT * FROM part WHERE partnumber IN (:partnumber)")
    List<Part> getByPartnumber(String partnumber);

    @Insert
    void insertAll(Part... parts);

    @Delete
    void delete(Part part);
}
