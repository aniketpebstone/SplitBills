package org.aniket.splitbills.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "session")
public class Session {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    @NonNull
    private String name;

    public Session()
    {}
    @Ignore
    public Session(String name)
    {
        this.name=name;
    }

    @Ignore
    public Session(int id,String name)
    {
        this.id=id;
        this.name=name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return id == session.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Session [id=" + id + ", name=" + name
                + "]";
    }
}
