package org.aniket.splitbills.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "person")
public class Person {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    @NonNull
    private String name;
    @NonNull
    @ColumnInfo(name = "session_id")
    private int sessionId;

    public Person()
    {}
    @Ignore
    public Person(String name,int sessionId)
    {
        this.name=name;
        this.sessionId=sessionId;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getSessionId() {
        return sessionId;
    }
    public void setSessionId(int sessionId) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name
                + ", sessionId="+sessionId+"]";
    }
}
