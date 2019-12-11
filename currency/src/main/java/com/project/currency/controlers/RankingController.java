package com.project.currency.controlers;

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

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.ArrayList;

@Controller
public class RankingController {

    private static String apiKeyNomics = "ba1909c740d22cc80430f8da9b9a8d19";

    public static String makeAPICallNomics(String coin)
            throws URISyntaxException, IOException {
        String response_content = "";
        String url = MessageFormat.format("https://api.nomics.com/v1/currencies/ticker?key=ba1909c740d22cc80430f8da9b9a8d19&ids={0}&interval=1d,30d&convert=USD", coin);
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

        JSONObject obj = (JSONObject) new JSONArray(response_content).get(0);
        String priceUSD= (String) obj.getString("price");

        return priceUSD;
    }


    @GetMapping("/ranking")
    public String showRanking(Model model){

        ArrayList<String> currencies = new ArrayList<>();
        currencies.add("dupa");
        currencies.add("dupa");
        currencies.add("dupa");
        model.addAttribute("list",currencies);

        return "ranking";
    }
}
