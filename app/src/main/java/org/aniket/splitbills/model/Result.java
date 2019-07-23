package org.aniket.splitbills.model;

public class Result {
    int receiver;
    String receiverName;
    int payer;
    String payerName;
    float amount;

    public Result(int receiver, String receiverName, int payer, String payerName, float amount) {
        this.receiver = receiver;
        this.receiverName = receiverName;
        this.payer = payer;
        this.payerName = payerName;
        this.amount = amount;
    }
}
