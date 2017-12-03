package com.google.android.gms.samples.vision.barcodereader.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by ioutd on 12/2/2017.
 */

@Database(entities = {Part.class}, version = 1)
public abstract class PartDatabase extends RoomDatabase {
    public abstract PartDao partDao();
}
