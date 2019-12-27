package com.project.currency.models;

import java.util.ArrayList;

public class ChartObject {
    private String currency;
    private ArrayList<String> timestamps;
    private ArrayList<Double> prices;

    public String getCurrency() {
        return currency;
    }

    public ArrayList<Double> getPrices() {
        return prices;
    }

    public ArrayList<String> getTimestamps() {
        return timestamps;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setPrices(ArrayList<Double> prices) {
        this.prices = prices;
    }

    public void setTimestamps(ArrayList<String> timestamps) {
        this.timestamps = timestamps;
    }
}
