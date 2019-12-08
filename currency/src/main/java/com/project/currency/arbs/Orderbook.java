package com.project.currency.arbs;

import java.util.ArrayList;

public class Orderbook {

    class Order {

        private Float value;
        private Float amount;

        Order (Float value, Float amount){
            this.value = value;
            this.amount = amount;
        }

        public Float getAmount() {
            return amount;
        }

        public Float getValue() {
            return value;
        }
    }

    private ArrayList<Order> bids;
    private ArrayList<Order> asks;

    public Orderbook () {
        bids = new ArrayList<>();
        asks = new ArrayList<>();
    }

    public void addToBids (Order order) {
        bids.add(order);
    }

    public void addToAsks (Order order) {
        asks.add(order);
    }

    public ArrayList<Order> getAsks() {
        return asks;
    }

    public ArrayList<Order> getBids() {
        return bids;
    }
}
