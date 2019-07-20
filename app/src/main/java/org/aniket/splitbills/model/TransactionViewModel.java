package org.aniket.splitbills.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.aniket.splitbills.dao.*;

import java.util.List;

public class TransactionViewModel extends AndroidViewModel {


    private TransactionRepository mRepository;
    private LiveData<List<Transaction>> mAllTransactions;

    public TransactionViewModel(@NonNull Application application) {
        super(application);
        mRepository = new TransactionRepository(application);
        mAllTransactions = mRepository.getAllTransactionsBySessionId();
    }

    public LiveData<List<Transaction>> getAllTransactionsBySessionId() { return mAllTransactions; }

    public void insert(Transaction transaction) { mRepository.insert(transaction); }

}
