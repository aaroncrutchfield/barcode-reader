package com.google.android.gms.samples.vision.barcodereader.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by ioutd on 12/2/2017.
 */

//Create instance using Singleton
//https://medium.com/@magdamiu/android-room-persistence-library-97ad0d25668e
@Database(entities = {Part.class}, version = 1)
public abstract class PartDatabase extends RoomDatabase {

    private static PartDatabase INSTANCE;

    public abstract PartDao partDao();

    public static PartDatabase getPartDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    PartDatabase.class, "part")
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }
}
