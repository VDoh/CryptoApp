package com.project.currency.arbs;

import com.project.currency.models.ArbModel;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainArbs {

    private static MainArbs instance = null;
    public static MainArbs getInstance()
    {
        if (instance == null)
            instance = new MainArbs();

        return instance;
    }

    private ArrayList<Exchange> exchanges;
    private ArrayList<String> currencies;
    private int delay = 2;

    public void run() {
        try {
            exchanges = new ArrayList<>();
            currencies = new ArrayList<>();
            fillCurriencies();
            fillExchangesList();
            Processor.getInstance().setCurrencies(currencies);

            final ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(exchanges.size() * currencies.size());

            for (int i = 0; i < exchanges.size(); i++){
                for (int j = 0; j < currencies.size(); j++) {
                    newFixedThreadPool.execute(new Scanner(exchanges.get(i).getName(), currencies.get(j), exchanges.get(i).getUrl(), delay));
                }
            }

            while (true) {
                try {
                    Thread.sleep(Duration.ofSeconds(delay).toMillis());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                final ExecutorService executorService = Executors.newSingleThreadExecutor();
                final Future<Map<String, Processor.Pair>> connectionFuture = executorService.submit(Processor.getInstance());
                Map<String, Processor.Pair> arbPairs = connectionFuture.get();
                executorService.shutdown();

                if (arbPairs != null) {
                    Data.getInstance().clearList();
                    for (Map.Entry<String, Processor.Pair> entry : arbPairs.entrySet()) {
                        Processor.Pair arbPair = entry.getValue();

                        ArbModel arb = new ArbModel(entry.getKey(), arbPair.getBidExch(), arbPair.getAskExch(), arbPair.getBid(), arbPair.getAsk(), arbPair.getArbVal());
                        Data.getInstance().addToList(arb);

                        System.out.println(entry.getKey());
                        System.out.println(" ");
                        System.out.println("ARB VALUE: " + arbPair.getArbVal());
                        System.out.println("BID: " + arbPair.getBidExch() + " = " + arbPair.getBid());
                        System.out.println("ASK: " + arbPair.getAskExch() + " = " + arbPair.getAsk());
                        System.out.println("------------");
                    }
                }
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void fillExchangesList () {
        exchanges.add(new Exchange("bitbay", "https://bitbay.net/API/Public/CURRENCYUSD/orderbook.json"));
        exchanges.add(new Exchange("bitmart", "https://openapi.bitmart.com/v2/symbols/CURRENCY_USDT/orders"));
    }

    private void fillCurriencies () {
        currencies.add("BTC");
        currencies.add("ETH");
        currencies.add("LTC");
        currencies.add("XRP");
    }
}
