package org.aniket.splitbills.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

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

    @Ignore
    public Person(int id,String name,int sessionId)
    {
        this.id=id;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return getId() == person.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name
                + ", sessionId="+sessionId+"]";
    }
}
