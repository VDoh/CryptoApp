package com.project.currency.arbs;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.Duration;

public class Processor implements Runnable {

    private Orderbook BitBay;
    private Orderbook BitMart;
    private int delay = 1;

    private Processor() {
        this.BitBay = new Orderbook();
        this.BitMart = new Orderbook();
    }

    private static Processor instance = null;
    public static Processor getInstance()
    {
        if (instance == null)
            instance = new Processor();

        return instance;
    }

    public void updateOrderbook (String Exchange, String data) {
        if (Exchange.equals("bitbay")) {
            updateBitbay(data);
        } else if (Exchange.equals("bitmart")) {
            updateBitmart(data);
        } else {
            System.out.printf("Other exchange");
        }
    }

    private Float[] castStringToArray (String data) {
        String[] items = data.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");

        Float[] results = new Float[items.length];
        for (int i = 0; i < items.length; i++) {
            try {
                results[i] = Float.parseFloat(items[i]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return results;
    }

    private void updateBitbay(String json) {
        JSONArray bidsArray = new JSONObject(json).getJSONArray("bids");
        JSONArray asksArray = new JSONObject(json).getJSONArray("asks");

        for (Object obj : bidsArray) {
            Float[] data = castStringToArray(obj.toString());
            Orderbook.Order order = BitBay.new Order(data[0], data[1]);
            BitBay.addToBids(order);
        }

        for (Object obj : asksArray) {
            Float[] data = castStringToArray(obj.toString());
            Orderbook.Order order = BitBay.new Order(data[0], data[1]);
            BitBay.addToAsks(order);
        }
    }

    private void updateBitmart(String json) {
        JSONArray bidsArray = new JSONObject(json).getJSONArray("buys");
        JSONArray asksArray = new JSONObject(json).getJSONArray("sells");

        for (Object obj : bidsArray) {
            JSONObject orderBid = new JSONObject(obj.toString());
            Orderbook.Order order = BitMart.new Order(Float.valueOf(orderBid.getString("price")), Float.valueOf(orderBid.getString("amount")));
            BitMart.addToBids(order);
        }

        for (Object obj : asksArray) {
            JSONObject orderAsk = new JSONObject(obj.toString());
            Orderbook.Order order = BitMart.new Order(Float.valueOf(orderAsk.getString("price")), Float.valueOf(orderAsk.getString("amount")));
            BitMart.addToAsks(order);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(Duration.ofSeconds(delay).toMillis());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }
    }
}
