package org.aniket.splitbills.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.*;

@Entity(tableName = "transaction")
public class Transaction {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @NonNull
    @ColumnInfo(name = "session_id")
    private int sessionId;

    @NonNull
    @ColumnInfo(name = "person_id")
    private int personId;

    private String expense;

    @NonNull
    private int amount;

    @NonNull
    @ColumnInfo(name = "create_date")
    private long createdDate;

    public Transaction()
    {}
    @Ignore
    public Transaction(int sessionId, int personId, String expense, int amount,long createdDate) {
        this.sessionId = sessionId;
        this.personId = personId;
        this.expense = expense;
        this.amount = amount;
        this.createdDate = createdDate;
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

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getExpense() {
        return expense;
    }

    public void setExpense(String expense) {
        this.expense = expense;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @NonNull
    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(@NonNull long createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Transaction[" +"id=" + id +", sessionId=" + sessionId +", personId=" + personId +", expense='" + expense +", amount=" + amount +", createdDate=" + createdDate +']';
    }
}
