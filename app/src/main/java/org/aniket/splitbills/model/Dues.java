package org.aniket.splitbills.model;

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
}
