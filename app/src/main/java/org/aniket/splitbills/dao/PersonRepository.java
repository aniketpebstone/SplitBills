package org.aniket.splitbills.dao;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import org.aniket.splitbills.model.ActiveSession;
import org.aniket.splitbills.model.Person;
import org.aniket.splitbills.model.Session;

import java.util.List;

public class PersonRepository {

    private PersonDao personDao;
    private LiveData<List<Person>> allPersons;

    public PersonRepository(Application application) {
        SplitBillsRoomDatabase db = SplitBillsRoomDatabase.getDatabase(application);
        personDao = db.personDao();
    }

    public LiveData<List<Person>> getAllPersonsBySessionId() {
        return personDao.getAllPersonsBySessionId(ActiveSession.sessionId);
    }

    public LiveData<Person> getPersonsById(int id) {
        return personDao.getPersonsByPersonId(id);
    }

    public void insert (Person person) {
        new insertAsyncTask(personDao).execute(person);
    }

    public void update (Person person) {
        new updateAsyncTask(personDao).execute(person);
    }

    public void delete (Person person) {
        new deleteAsyncTask(personDao).execute(person);
    }

    private static class insertAsyncTask extends AsyncTask<Person, Void, Void> {

        private PersonDao mAsyncTaskDao;

        insertAsyncTask(PersonDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Person... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Person, Void, Void> {

        private PersonDao mAsyncTaskDao;

        updateAsyncTask(PersonDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Person... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Person, Void, Void> {

        private PersonDao mAsyncTaskDao;

        deleteAsyncTask(PersonDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Person... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
