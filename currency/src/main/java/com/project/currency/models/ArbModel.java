package com.project.currency.models;

public class ArbModel {

    private String currency;
    private String bidExch;
    private String askExch;
    private Float bid;
    private Float ask;
    private Float arbVal;

    public ArbModel(String currency, String bidExch, String askExch, Float bid, Float ask, Float arbVal){
        super();
        this.currency = currency;
        this.bidExch = bidExch;
        this.askExch = askExch;
        this.bid = bid;
        this.ask = ask;
        this.arbVal = arbVal;
    }

    public String getCurrency() {
        return currency;
    }

    public String getBidExch() {
        return bidExch;
    }

    public String getAskExch() {
        return askExch;
    }

    public Float getBid() {
        return bid;
    }

    public Float getAsk() {
        return ask;
    }

    public Float getArbVal() {
        return arbVal;
    }

    public String toString() {
        return "{currency: " + this.currency + ", bid: " + this.bid + ", ask: " + this.ask + ", value: " + arbVal + "}";
    }
}
