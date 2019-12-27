package com.project.currency.controlers;

import com.project.currency.RankingUtils.MyRunnableApi;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class RankingController {

    private static String apiKeyNomics = "ba1909c740d22cc80430f8da9b9a8d19";

    public static List<CurrencyInfo> makeAPICallNomics()
            throws URISyntaxException, IOException, InterruptedException {

        MyRunnableApi.Info.clear();

        ArrayList<String> currencies = new ArrayList<>();
        currencies.add("BTC");
        currencies.add("ETH");
        currencies.add("XRP");
        currencies.add("USDT");
        currencies.add("LTC");
        currencies.add("EOS");
        currencies.add("BNB");
        currencies.add("BSV");
        currencies.add("XTZ");
        currencies.add("XLM");
        currencies.add("LEO");
        currencies.add("ATOM");
        currencies.add("HC");
        currencies.add("BAT");
        currencies.add("TUSD");
        currencies.add("EKT");
        currencies.add("LUNA");
        currencies.add("SC");;
        currencies.add("RDD");
        ArrayList<CurrencyInfo> Info = new ArrayList<>();

        int numberOfTasks = 19;
        ExecutorService executor= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        try{
            for ( int i=0; i < numberOfTasks; i++){
                executor.execute(new MyRunnableApi(currencies.get(i)));
            }
            executor.shutdown();
        }catch(Exception err){
            err.printStackTrace();
        }
        executor.shutdown(); // once you are done with ExecutorService

/*
        for (String currency : currencies)
        {
            String response_content = "";
            String url = MessageFormat.format("https://api.nomics.com/v1/currencies/ticker?key=ba1909c740d22cc80430f8da9b9a8d19&ids={0}&interval=1d,30d&convert=USD",currency);
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
            System.out.println(newData.getCurrency() + "  " + newData.getLogo() + "  " + newData.getMarketCap() + "  " + newData.getPctChange()  + "  " + newData.getCurrency());
        } */
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        Collections.sort(MyRunnableApi.Info);
        return MyRunnableApi.Info;
    }


    @GetMapping("/ranking")
    public String showRanking(Model model) throws IOException, URISyntaxException, InterruptedException {

        List<CurrencyInfo> newdata = makeAPICallNomics();


        model.addAttribute("list",newdata);

        return "ranking";
    }
}
