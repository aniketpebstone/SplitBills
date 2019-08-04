package org.aniket.splitbills.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.aniket.splitbills.dao.SplitRepository;
import org.aniket.splitbills.dao.TransactionRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class SpliltViewModel extends AndroidViewModel {


    private SplitRepository mRepository;
    private LiveData<List<Split>> mAllTransactions;

    public SpliltViewModel(@NonNull Application application) {
        super(application);
        mRepository = new SplitRepository(application);
    }

    public LiveData<List<Split>> getAllSplitsBySessionId() { return mRepository.getAllSplitsBySessionId(); }

    public LiveData<List<Split>> getAllSplitsByTxnId(int txnId) { return mRepository.getAllSplitsByTxnId(txnId); }

    public void insert(Split split) { mRepository.insert(split); }

}
