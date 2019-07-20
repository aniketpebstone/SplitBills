package org.aniket.splitbills.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import org.aniket.splitbills.model.*;


import java.util.List;

@Dao
public interface PersonDao {

    @Insert
    void insert(Person person);

    @Update
    void update(Person person);

    @Query("Delete FROM person WHERE id=:id")
    void deleteById(int id);

    @Query("SELECT * from person where sessionId=:sessionId ORDER BY id")
    LiveData<List<Person>> getAllPersonsBySessionId(int sessionId);
}
