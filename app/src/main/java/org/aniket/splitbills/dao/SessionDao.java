package org.aniket.splitbills.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import org.aniket.splitbills.model.Session;

import java.util.List;

@Dao
public interface SessionDao {

    @Insert
    void insert(Session session);

    @Update
    void update(Session session);

    @Query("Delete FROM session WHERE id=:id")
    void deleteById(int id);

    @Query("SELECT * from session ORDER BY id")
    LiveData<List<Session>> getAllSessions();
}
