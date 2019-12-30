package com.project.currency.models;

public class HistoryModelLite {

    private String coin;
    private Float price;
    private String date;

    public HistoryModelLite (String coin, Float price, String date) {
        this.coin = coin;
        this.price = price;
        this.date = date;
    }

    public String getCoin() {
        return coin;
    }

    public String getDate() {
        return date;
    }

    public Float getPrice() {
        return price;
    }
}
