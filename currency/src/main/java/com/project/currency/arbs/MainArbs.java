package com.project.currency.arbs;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainArbs {

    private ArrayList<Exchange> exchanges;
    private int delaay = 1;

    public MainArbs() {
        exchanges = new ArrayList<>();
        fillExchangesList();

        final ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(3);

        for (int i = 0; i < exchanges.size(); i++){
            newFixedThreadPool.execute(new Scanner(exchanges.get(i).getName(), exchanges.get(i).getUrl(), 1));
        }
        newFixedThreadPool.execute(Processor.getInstance());

    }

    private void fillExchangesList () {
        exchanges.add(new Exchange("bitbay", "https://bitbay.net/API/Public/BTCUSD/orderbook.json"));
        exchanges.add(new Exchange("bitmart", "https://openapi.bitmart.com/v2/symbols/BTC_USDT/orders"));
    }
}
