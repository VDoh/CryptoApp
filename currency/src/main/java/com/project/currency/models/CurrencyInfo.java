package com.project.currency.models;

public class CurrencyInfo
{
    private String Currency;
    private String Price;
    private String PctChange;
    private String MarketCap;
    private String Logo;

    public CurrencyInfo(){
        super();
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        this.Currency = currency;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        this.Price = price;
    }

    public String getPctChange() {
        return PctChange;
    }

    public void setPctChange(String pctChange) {
        this.PctChange = pctChange;
    }

    public String getMarketCap() {
        return MarketCap;
    }

    public void setMarketCap(String marketCap) {
        this.MarketCap = marketCap;
    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String logo) {
        this.Logo = logo;
    }

}
