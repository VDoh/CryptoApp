package com.project.currency.controlers;

import com.project.currency.models.LoginForm;
import org.apache.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.json.*;

import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
public class CryptoController {
    private static String apiKeyCoinMarket = "31eaa970-1cc5-4934-8004-548bc722d381";
    private static String apiKeyNomics = "ba1909c740d22cc80430f8da9b9a8d19";

    public static String makeAPICallCoinMarket(String uri, List<NameValuePair> parameters)
            throws URISyntaxException, IOException {
        String response_content = "";

        URIBuilder query = new URIBuilder(uri);
        query.addParameters(parameters);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(query.build());

        request.setHeader(HttpHeaders.ACCEPT, "application/json");
        request.addHeader("X-CMC_PRO_API_KEY", apiKeyCoinMarket);

        CloseableHttpResponse response = client.execute(request);

        try {
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            response_content = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }

        JSONObject obj = (JSONObject) new JSONObject(response_content).get("data");
        JSONObject objTemp= (JSONObject) obj.get("quote");
        JSONObject objTemp2= (JSONObject) objTemp.get("USD");
        Double priceUSD = (Double) objTemp2.get("price");

        String priceStirng= priceUSD.toString();

        return priceStirng;
    }
    public static String makeAPICallNomics()
            throws URISyntaxException, IOException {
            String response_content = "";
            String url = "https://api.nomics.com/v1/currencies/ticker?key=ba1909c740d22cc80430f8da9b9a8d19&ids=BTC&interval=1d,30d&convert=USD";
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

    @GetMapping("/crypto")
    public String showCrypto(@ModelAttribute(name = "loginForm") LoginForm loginForm, Model model){

        model.addAttribute("misraMessages", getCryptoList());
        String uri = "https://pro-api.coinmarketcap.com/v1/tools/price-conversion";
        List<NameValuePair> paratmers = new ArrayList<NameValuePair>();
        paratmers.add(new BasicNameValuePair("id","1"));
        paratmers.add(new BasicNameValuePair("amount","1"));
        paratmers.add(new BasicNameValuePair("convert","USD"));

        try {
            String result = makeAPICallCoinMarket(uri, paratmers);
            String result2 = makeAPICallNomics();
            System.out.println(result);
            System.out.println( result2);
        } catch (IOException e) {
            System.out.println("Error: cannont access content - " + e.toString());
        } catch (URISyntaxException e) {
            System.out.println("Error: Invalid URL " + e.toString());
        }

        return "crypto";
    }

    @ModelAttribute("cryptoList")
    public List<String> getCryptoList() {
        List<String> cryptoList = new ArrayList<String>();
        cryptoList.add("BTC");
        cryptoList.add("ETH");
        cryptoList.add("BCH");
        cryptoList.add("LTC");
        cryptoList.add("BNB");
        return cryptoList;
    }
}
