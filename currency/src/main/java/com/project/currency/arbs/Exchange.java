package com.project.currency.arbs;


public class Exchange {

    private String name;
    private String url;

    public Exchange(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
