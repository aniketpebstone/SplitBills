package org.aniket.splitbills.dao;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import org.aniket.splitbills.model.Session;

import java.util.List;

public class SessionRepository {

    private SessionDao sesssionDao;
    private LiveData<List<Session>> allSessions;

    public SessionRepository(Application application) {
        SplitBillsRoomDatabase db = SplitBillsRoomDatabase.getDatabase(application);
        sesssionDao = db.sessionDao();
    }

    public LiveData<List<Session>> getAllSessions() {
        allSessions = sesssionDao.getAllSessions();
        return allSessions;
    }

    public LiveData<Session> getSessionById(int id) {
        return sesssionDao.getSessionById(id);
    }

    public void insert (Session session) {
        new insertAsyncTask(sesssionDao).execute(session);
    }

    public void update (Session session) {
        new updateAsyncTask(sesssionDao).execute(session);
    }

    private static class insertAsyncTask extends AsyncTask<Session, Void, Void> {

        private SessionDao mAsyncTaskDao;

        insertAsyncTask(SessionDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Session... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Session, Void, Void> {

        private SessionDao mAsyncTaskDao;

        updateAsyncTask(SessionDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Session... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }
}
