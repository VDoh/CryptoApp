package com.project.currency.controlers;

import com.project.currency.models.ApiCall;
import com.project.currency.models.LoginForm;
import org.apache.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.MessageFormat;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
<<<<<<< HEAD
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("crypto")
=======


@Controller
@RequestMapping("/crypto")
>>>>>>> master
public class CryptoController {
    private static String apiKeyCoinMarket = "31eaa970-1cc5-4934-8004-548bc722d381";
    private static String apiKeyNomics = "ba1909c740d22cc80430f8da9b9a8d19";

    @RequestMapping(method = RequestMethod.GET)
<<<<<<< HEAD
    public String initForm(Model model) {
        ApiCall crypto = new ApiCall();
        model.addAttribute("crypto", crypto);
        getCryptoList(model);
        return "colour";
    }


    public static String makeAPICall(String uri, List<NameValuePair> parameters)
=======
    public String showYourAss(){
        return "crypto";
    }

    public static String makeAPICallCoinMarket(String uri, List<NameValuePair> parameters,String coin)
>>>>>>> master
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

<<<<<<< HEAD
    @RequestMapping(method = RequestMethod.POST)
    public String showCrypto(@ModelAttribute(name = "apiValues") ApiCall apiCall, Model model){
=======
        return priceUSD;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String showCrypto(@ModelAttribute(name = "ApiCall") ApiCall apiCall, Model model){
        String coin = apiCall.getCurrency();
        System.out.println(coin);
        model.addAttribute("misraMessages", getCryptoList());
>>>>>>> master
        String uri = "https://pro-api.coinmarketcap.com/v1/tools/price-conversion";
        List<NameValuePair> paratmers = new ArrayList<NameValuePair>();

        paratmers.add(new BasicNameValuePair("symbol",coin));
        paratmers.add(new BasicNameValuePair("amount","1"));
        paratmers.add(new BasicNameValuePair("convert","USD"));

        try {
            String result = makeAPICallCoinMarket(uri, paratmers,coin);
            String result2 = makeAPICallNomics(coin);
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
    public void getCryptoList(Model model) {
        List<String> crypto = new ArrayList<String>();
        crypto.add("BTC");
        crypto.add("ETH");
        crypto.add("BCH");
        crypto.add("LTC");
        crypto.add("BNB");
        model.addAttribute("crypto", crypto);
    }
}
