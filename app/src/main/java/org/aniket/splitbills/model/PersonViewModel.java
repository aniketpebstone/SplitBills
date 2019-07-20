package org.aniket.splitbills.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.aniket.splitbills.dao.SessionRepository;

import java.util.List;
import org.aniket.splitbills.model.*;
import org.aniket.splitbills.dao.*;

public class PersonViewModel extends AndroidViewModel {


    private PersonRepository mRepository;
    private LiveData<List<Person>> mAllPersons;

    public PersonViewModel(@NonNull Application application) {
        super(application);
        mRepository = new PersonRepository(application);
        mAllPersons = mRepository.getAllPersonsBySessionId();
    }

    public LiveData<List<Person>> getAllPersonsBySessionId() { return mAllPersons; }

    public void insert(Person person) { mRepository.insert(person); }

}
