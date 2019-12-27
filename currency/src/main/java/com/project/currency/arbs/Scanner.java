package com.project.currency.arbs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;

public class Scanner implements Runnable {

    private String exchange;
    private String currency;
    private String url;
    private int delay;

    public Scanner(String exchange, String currency, String url, int delay) {
        System.out.println("Run new Scanner for " + exchange);
        this.exchange = exchange;
        this.currency = currency;
        this.url = url.replaceAll("CURRENCY", currency);
        this.delay = delay;
    }

    private String getData(){
        try {
            System.out.println("Getting data...");
            URL url = new URL(this.url);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            System.out.println(currency);
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            } else {
                System.out.println("GET request not worked");
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(Duration.ofSeconds(delay).toMillis());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            String data = getData();
            System.out.println(data);
            Processor.getInstance().updateOrderbook(exchange, currency, data);
        }
    }
}
