package com.google.android.gms.samples.vision.barcodereader.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Aaron Crutchfield on 4/5/2018.
 */

@Database(entities = {Part.class, SummaryPart.class}, version = 6)
public abstract class InventoryDatabase extends RoomDatabase {

    private static InventoryDatabase INSTANCE;

    public abstract PartDao partDao();
    public abstract SummaryPartDao summaryPartDao();

    public static InventoryDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room
                    .databaseBuilder(
                            context.getApplicationContext(),
                            InventoryDatabase.class,
                            "inventory")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()   // TODO: 4/18/2018 getAppDatabase - REMOVE *FOR TEST RUN ONLY*
                    .build();
        }
        return INSTANCE;
    }
}
