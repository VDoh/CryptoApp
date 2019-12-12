package com.project.currency.RankingUtils;

import com.project.currency.models.CurrencyInfo;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class MyRunnableApi implements Runnable{
    String value;
    public static List<CurrencyInfo> Info = new ArrayList<>();
    public MyRunnableApi(String i){
        this.value = i;
    }
    public void run(){
        try{
            String response_content = "";
            String url = MessageFormat.format("https://api.nomics.com/v1/currencies/ticker?key=ba1909c740d22cc80430f8da9b9a8d19&ids={0}&interval=1d,30d&convert=USD",value);
            URIBuilder query = new URIBuilder(url);
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet request = new HttpGet(query.build());
            CloseableHttpResponse response = client.execute(request);

            try {
                System.out.println(response.getStatusLine());
                HttpEntity entity = response.getEntity();
                response_content = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }

            CurrencyInfo newData = new CurrencyInfo();

            JSONObject obj = (JSONObject) new JSONArray(response_content).get(0);
            newData.setPrice((String) obj.getString("price"));
            newData.setCurrency((String) obj.getString("symbol"));
            newData.setLogo((String) obj.getString("logo_url"));
            newData.setMarketCap((String) obj.getString("market_cap"));
            JSONObject obj1= (JSONObject) obj.get("1d");
            newData.setPctChange(obj1.getString("price_change_pct"));
            Info.add(newData);
            System.out.println(Info.size());

        }catch(Exception err){
            err.printStackTrace();
        }
    }

}