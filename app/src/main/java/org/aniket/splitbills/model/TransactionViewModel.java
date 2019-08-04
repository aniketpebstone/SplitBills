package org.aniket.splitbills.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.aniket.splitbills.dao.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class TransactionViewModel extends AndroidViewModel {


    private TransactionRepository mRepository;
    private LiveData<List<Transaction>> mAllTransactions;

    public TransactionViewModel(@NonNull Application application) {
        super(application);
        mRepository = new TransactionRepository(application);
    }

    public LiveData<List<Transaction>> getAllTransactionsBySessionId() { return mRepository.getAllTransactionsBySessionId(); }

    public Long insert(Transaction transaction) throws ExecutionException, InterruptedException { return mRepository.insert(transaction); }

}
