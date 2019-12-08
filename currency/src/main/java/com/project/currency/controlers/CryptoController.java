package com.project.currency.controlers;

import com.project.currency.models.ApiCall;
import com.project.currency.models.LoginForm;
import org.apache.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("crypto")
public class CryptoController {
    private static String apiKey = "31eaa970-1cc5-4934-8004-548bc722d381";

    @RequestMapping(method = RequestMethod.GET)
    public String initForm(Model model) {
        ApiCall crypto = new ApiCall();
        model.addAttribute("crypto", crypto);
        getCryptoList(model);
        return "colour";
    }


    public static String makeAPICall(String uri, List<NameValuePair> parameters)
            throws URISyntaxException, IOException {
        String response_content = "";

        URIBuilder query = new URIBuilder(uri);
        query.addParameters(parameters);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(query.build());

        request.setHeader(HttpHeaders.ACCEPT, "application/json");
        request.addHeader("X-CMC_PRO_API_KEY", apiKey);

        CloseableHttpResponse response = client.execute(request);

        try {
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            response_content = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }

        return response_content;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String showCrypto(@ModelAttribute(name = "apiValues") ApiCall apiCall, Model model){
        String uri = "https://pro-api.coinmarketcap.com/v1/tools/price-conversion";
        List<NameValuePair> paratmers = new ArrayList<NameValuePair>();
        paratmers.add(new BasicNameValuePair("id","1"));
        paratmers.add(new BasicNameValuePair("amount","1"));
        paratmers.add(new BasicNameValuePair("convert","USD"));

        try {
            String result = makeAPICall(uri, paratmers);
            System.out.println(result);
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
