package org.aniket.splitbills.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import org.aniket.splitbills.model.Transaction;

import java.util.List;

@Dao
public interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    long insert(Transaction transaction);

    @Update
    void update(Transaction transaction);

    @Query("Delete FROM `transaction` WHERE id=:id")
    void deleteById(int id);

    @Query("Delete FROM `transaction` WHERE session_id=:sessionId")
    void deleteBySessionId(int sessionId);

    @Query("Delete FROM `transaction` WHERE person_id=:personId")
    void deleteByPersonId(int personId);

    @Query("SELECT * from `transaction` where session_id=:sessionId ORDER BY create_date")
    LiveData<List<Transaction>> getAllTransactionsBySessionId(int sessionId);

    @Query("SELECT * from `transaction` where person_id=:personId ORDER BY create_date")
    LiveData<List<Transaction>> getAllTransactionsByPersonId(int personId);
}
