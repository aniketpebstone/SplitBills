package org.aniket.splitbills.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import org.aniket.splitbills.model.Split;

import java.util.List;

@Dao
public interface SplitDao {

    @Insert
    void insert(Split split);

    @Update
    void update(Split split);

    @Query("Delete FROM split WHERE id=:id")
    void deleteById(int id);

    @Query("Delete FROM split WHERE session_id=:sessionId")
    void deleteBySessionId(int sessionId);

    @Query("Delete FROM split WHERE transaction_id=:txnId")
    void deleteByTxnId(int txnId);

    @Query("SELECT * from split where session_id=:sessionId")
    LiveData<List<Split>> getAllSplitsBySessionId(int sessionId);

    @Query("SELECT * from split where transaction_id=:txnId")
    LiveData<List<Split>> getAllSplitsByTxnId(int txnId);
}
