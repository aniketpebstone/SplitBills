package org.aniket.splitbills.model;

import java.util.Objects;

public class Dues {
    int personId;
    float duesReceivable;
    float duesPayable;
    float difference;

    @Override
    public String toString() {
        return "Dues[" +
                "personId=" + personId +
                ", duesReceivable=" + duesReceivable +
                ", duesPayable=" + duesPayable +
                ", difference=" + difference +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dues dues = (Dues) o;
        return personId == dues.personId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId);
    }
}
