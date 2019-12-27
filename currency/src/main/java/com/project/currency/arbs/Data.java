package com.project.currency.arbs;

import com.project.currency.models.ArbModel;

import java.util.ArrayList;

public class Data {

    private ArrayList<ArbModel> arbsList = new ArrayList<>();

    private static Data instance = null;
    public static Data getInstance()
    {
        if (instance == null)
            instance = new Data();

        return instance;
    }

    public void addToList (ArbModel arb) {
        arbsList.add(arb);
    }

    public void clearList () {
        arbsList.clear();
    }

    public ArrayList<ArbModel> getArbsList() {
        return arbsList;
    }
}
