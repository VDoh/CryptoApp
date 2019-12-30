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

}
