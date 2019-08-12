package org.aniket.splitbills.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.aniket.splitbills.dao.PersonRepository;
import org.aniket.splitbills.dao.SplitRepository;

import java.util.List;

public class SplitViewModel extends AndroidViewModel {


    private SplitRepository mRepository;

    public SplitViewModel(@NonNull Application application) {
        super(application);
        mRepository = new SplitRepository(application);
    }

    public LiveData<List<Split>> getAllSplitsByTxnId(int txnId) {
        return mRepository.getAllSplitsByTxnId(txnId);
    }


    public void insert(Split split) { mRepository.insert(split); }

//    public void update(Split split) { mRepository.update(split); }
//
//    public void delete(Split split) { mRepository.delete(split); }

}
