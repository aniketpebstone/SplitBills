package org.aniket.splitbills.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.aniket.splitbills.dao.SessionRepository;

import java.util.List;

public class SessionViewModel  extends AndroidViewModel {

    private SessionRepository mRepository;
    private LiveData<List<Session>> mAllSessions;

    public SessionViewModel(@NonNull Application application) {
        super(application);
        mRepository = new SessionRepository(application);
        mAllSessions = mRepository.getAllSessions();
    }

    public LiveData<List<Session>> getAllSessions() { return mAllSessions; }

    public void insert(Session session) { mRepository.insert(session); }

}
