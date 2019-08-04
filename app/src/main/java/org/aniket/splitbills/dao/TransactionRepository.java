package org.aniket.splitbills.dao;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import org.aniket.splitbills.model.ActiveSession;
import org.aniket.splitbills.model.Person;
import org.aniket.splitbills.model.Transaction;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class TransactionRepository {

    private TransactionDao txnDao;
    private LiveData<List<Transaction>> allTxns;

    public TransactionRepository(Application application) {
        SplitBillsRoomDatabase db = SplitBillsRoomDatabase.getDatabase(application);
        txnDao = db.transactionDao();
    }

    public LiveData<List<Transaction>> getAllTransactionsBySessionId() {
        return txnDao.getAllTransactionsBySessionId(ActiveSession.sessionId);
    }

    public Long insert (Transaction txn) throws ExecutionException, InterruptedException {
        return new insertAsyncTask(txnDao).execute(txn).get();
    }

    private static class insertAsyncTask extends AsyncTask<Transaction, Void, Long> {

        private TransactionDao mAsyncTaskDao;

        insertAsyncTask(TransactionDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Long doInBackground(final Transaction... params) {
            return mAsyncTaskDao.insert(params[0]);
        }
    }
}
