package org.aniket.splitbills.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "split")
public class Split {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @NonNull
    @ColumnInfo(name = "session_id")
    private int sessionId;

    @NonNull
    @ColumnInfo(name = "transaction_id")
    private int transactionId;

    @NonNull
    @ColumnInfo(name = "person_id")
    private int personId;

    @NonNull
    @ColumnInfo(name = "split_among")
    private int splitAmong;

    @NonNull
    @ColumnInfo(name = "split_amount")
    private float splitAmount;

    public Split()
    {}

    @Ignore
    public Split(int sessionId, int transactionId, int personId, int splitAmong, float splitAmount) {
        this.sessionId = sessionId;
        this.transactionId = transactionId;
        this.personId = personId;
        this.splitAmong = splitAmong;
        this.splitAmount = splitAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getSplitAmong() {
        return splitAmong;
    }

    public void setSplitAmong(int splitAmong) {
        this.splitAmong = splitAmong;
    }

    public float getSplitAmount() {
        return splitAmount;
    }

    public void setSplitAmount(float splitAmount) {
        this.splitAmount = splitAmount;
    }

    @Override
    public String toString() {
        return "Split[" +"id=" + id +", sessionId=" + sessionId +", transactionId=" + transactionId +", personId=" + personId +", splitAmong=" + splitAmong +", splitAmount=" + splitAmount +']';
    }
}
