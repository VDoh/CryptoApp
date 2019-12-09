package com.project.currency.models;

public class ArbModel {

    private String bidExch;
    private String askExch;
    private Float bid;
    private Float ask;
    private Float arbVal;

    public ArbModel(){
        super();
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
}
