package org.aniket.splitbills.dao;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import org.aniket.splitbills.model.*;

import java.util.List;

public class SplitRepository {

    private SplitDao splitDao;
    private LiveData<List<Split>> allSplits;

    public SplitRepository(Application application) {
        SplitBillsRoomDatabase db = SplitBillsRoomDatabase.getDatabase(application);
        splitDao = db.splitDao();
    }

    public LiveData<List<Split>> getAllSplitsBySessionId() {
        return splitDao.getAllSplitsBySessionId(ActiveSession.sessionId);
    }

    public LiveData<List<Split>> getAllSplitsByTxnId(int txnId) {
        return splitDao.getAllSplitsByTxnId(txnId);
    }

    public void insert (Split split) {
        new insertAsyncTask(splitDao).execute(split);
    }

    private static class insertAsyncTask extends AsyncTask<Split, Void, Void> {

        private SplitDao mAsyncTaskDao;

        insertAsyncTask(SplitDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Split... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
