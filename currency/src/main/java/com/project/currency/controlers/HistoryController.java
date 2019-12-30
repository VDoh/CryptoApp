package com.project.currency.controlers;

import com.project.currency.models.HistoryModel;
import com.project.currency.models.HistoryModelLite;
import com.project.currency.services.HistoryMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HistoryController {
    @Autowired
    private HistoryMeasureService service;

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public String showHistory(Model model){

        List<HistoryModel> history = service.getAllHistory();
        List<HistoryModelLite> historyModelsLite = new ArrayList<>();

        for (HistoryModel historyModel : history) {
            String localCoin = "";
            Float localPrice = 0.0f;

            if (historyModel.getBtc() != -1) {
                localCoin = "BTC";
                localPrice = historyModel.getBtc();
            }

            if (historyModel.getEth() != -1) {
                localCoin = "ETH";
                localPrice = historyModel.getEth();
            }

            if (historyModel.getUsdt() != -1) {
                localCoin = "USDT";
                localPrice = historyModel.getUsdt();
            }

            if (historyModel.getXrp() != -1) {
                localCoin = "XRP";
                localPrice = historyModel.getXrp();
            }

            if (historyModel.getBch() != -1) {
                localCoin = "BCH";
                localPrice = historyModel.getBch();
            }

            historyModelsLite.add(new HistoryModelLite(localCoin, localPrice, historyModel.getDate()));
        }

        model.addAttribute("history", historyModelsLite);

        return "history";
    }
}
