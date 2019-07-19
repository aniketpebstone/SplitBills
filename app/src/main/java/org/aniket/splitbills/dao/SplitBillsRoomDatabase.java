package org.aniket.splitbills.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.aniket.splitbills.model.Session;

@Database(entities = {Session.class}, version = 1,exportSchema = false)
public abstract class SplitBillsRoomDatabase extends RoomDatabase {

    private static volatile SplitBillsRoomDatabase INSTANCE;

    static SplitBillsRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SplitBillsRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SplitBillsRoomDatabase.class, "split_bills_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract SessionDao sessionDao();
}
