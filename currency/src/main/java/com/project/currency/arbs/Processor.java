package com.project.currency.arbs;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class Processor implements Callable<Map<String, Processor.Pair> > {

    public class Pair {
        private String bidExch;
        private String askExch;
        private Float bid;
        private Float ask;
        private Float arbVal;

        Pair (String bidExch, String askExch, Float bid, Float ask) {
            this.bidExch = bidExch;
            this.askExch = askExch;
            this.bid = bid;
            this.ask = ask;
            this.arbVal = null;
        }

        public Float getArbVal() {
            return arbVal;
        }

        public Float getAsk() {
            return ask;
        }

        public Float getBid() {
            return bid;
        }

        public String getAskExch() {
            return askExch;
        }

        public String getBidExch() {
            return bidExch;
        }
    }

    private Map<String, Orderbook> BitBay;
    private Map<String, Orderbook> BitMart;
    private ArrayList<String> currencies;
    private Map<String, Pair> arbs;

    private Processor() {
        this.BitBay = new HashMap<>();
        this.BitMart = new HashMap<>();
        this.arbs = new HashMap<>();
    }

    private static Processor instance = null;
    public static Processor getInstance()
    {
        if (instance == null)
            instance = new Processor();

        return instance;
    }

    public void setCurrencies (ArrayList<String> currencies) {
        this.currencies = currencies;
        createOrderbooks();
    }

    public void updateOrderbook (String Exchange, String currency, String data) {
        if (Exchange.equals("bitbay")) {
            updateBitbay(data, currency);
        } else if (Exchange.equals("bitmart")) {
            updateBitmart(data, currency);
        } else {
            if (MainArbs.getInstance().getLogs())
                System.out.printf("Other exchange");
        }
    }

    private void createOrderbooks () {
        for (String currency : currencies) {
            BitBay.put(currency, new Orderbook());
            BitMart.put(currency, new Orderbook());
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

    private void updateBitbay(String json, String currency) {
        if (MainArbs.getInstance().getLogs())
            System.out.println("Update Bitbay " + currency);

        if (BitBay.get(currency) == null)
            BitBay.put(currency, new Orderbook());

        if (json != null) {
            BitBay.get(currency).clearOrderbook();
            JSONArray bidsArray = new JSONObject(json).getJSONArray("bids");
            JSONArray asksArray = new JSONObject(json).getJSONArray("asks");

            for (Object obj : bidsArray) {
                Float[] data = castStringToArray(obj.toString());
                Orderbook.Order order = BitBay.get(currency).new Order(data[0], data[1]);
                BitBay.get(currency).addToBids(order);
            }

            for (Object obj : asksArray) {
                Float[] data = castStringToArray(obj.toString());
                Orderbook.Order order = BitBay.get(currency).new Order(data[0], data[1]);
                BitBay.get(currency).addToAsks(order);
            }
        }
    }

    private void updateBitmart(String json, String currency) {
        if (MainArbs.getInstance().getLogs())
            System.out.println("Update Bitmart " + currency);

        if (BitMart.get(currency) == null)
            BitMart.put(currency, new Orderbook());

        if (json != null) {
            BitMart.get(currency).clearOrderbook();
            JSONArray bidsArray = new JSONObject(json).getJSONArray("buys");
            JSONArray asksArray = new JSONObject(json).getJSONArray("sells");

            for (Object obj : bidsArray) {
                JSONObject orderBid = new JSONObject(obj.toString());
                Orderbook.Order order = BitMart.get(currency).new Order(Float.valueOf(orderBid.getString("price")), Float.valueOf(orderBid.getString("amount")));
                BitMart.get(currency).addToBids(order);
            }

            for (Object obj : asksArray) {
                JSONObject orderAsk = new JSONObject(obj.toString());
                Orderbook.Order order = BitMart.get(currency).new Order(Float.valueOf(orderAsk.getString("price")), Float.valueOf(orderAsk.getString("amount")));
                BitMart.get(currency).addToAsks(order);
            }
        }
    }

    private Map<String, Pair> compareData () {
        for (String currency : currencies) {
            if (!BitMart.get(currency).getBids().isEmpty() && !BitMart.get(currency).getAsks().isEmpty() && !BitBay.get(currency).getBids().isEmpty() && !BitBay.get(currency).getAsks().isEmpty()) {
                Pair pair1 = new Pair("BitMart", "BitBay", BitMart.get(currency).getBids().stream().max(Comparator.comparing(Orderbook.Order::getValue)).get().getValue(),
                        BitBay.get(currency).getAsks().stream().min(Comparator.comparing(Orderbook.Order::getValue)).get().getValue());

                Pair pair2 = new Pair("BitMart", "BitBay", BitBay.get(currency).getBids().stream().max(Comparator.comparing(Orderbook.Order::getValue)).get().getValue(),
                        BitMart.get(currency).getAsks().stream().min(Comparator.comparing(Orderbook.Order::getValue)).get().getValue());

                pair1.arbVal = calculateArb(pair1);
                pair2.arbVal = calculateArb(pair2);

                if (pair1.arbVal >= pair2.arbVal) {
                    arbs.put(currency, pair1);
                } else {
                    arbs.put(currency, pair2);
                }
            }
        }

        return arbs;
    }

    public Float calculateArb (Pair pair) {
        return ((pair.bid - pair.ask) / pair.ask) * 100;
    }

    @Override
    public Map<String, Pair>  call() {
        if (MainArbs.getInstance().getLogs())
            System.out.println(arbs.size());

        return compareData();
    }
}
