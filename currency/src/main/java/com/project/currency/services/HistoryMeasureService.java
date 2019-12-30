package com.project.currency.services;

import com.project.currency.models.HistoryModel;
import com.project.currency.models.User;
import com.project.currency.repositories.CurrencyMeasureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HistoryMeasureService {

    @Autowired
    private CurrencyMeasureRepo cm;

    public List<HistoryModel> getAllHistory(){
        return  (List<HistoryModel>) cm.findAll();

    }

    public void addMeasure (Long id_user, String coin, float price, String date) {
        HistoryModel model = new HistoryModel();

        model.setId_user(id_user);
        model.setEth(-1);
        model.setBtc(-1);
        model.setUsdt(-1);
        model.setXrp(-1);
        model.setBch(-1);
        model.setDate(date);

        if (coin.equals("ETH"))
            model.setEth(price);

        if (coin.equals("BTC"))
            model.setBtc(price);

        if (coin.equals("USDT"))
            model.setUsdt(price);

        if (coin.equals("XRP"))
            model.setXrp(price);

        if (coin.equals("BCH"))
            model.setBch(price);

        cm.save(model);
    }

}
