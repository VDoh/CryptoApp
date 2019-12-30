package com.project.currency.models;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name = "currency_measure")
public class HistoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long id_user;
    private float eth;
    private float btc;
    private float usdt;
    private float xrp;
    private float bch;
    private String date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public float getEth() {
        return eth;
    }

    public void setEth(float eth) {
        this.eth = eth;
    }

    public float getBtc() {
        return btc;
    }

    public void setBtc(float btc) {
        this.btc = btc;
    }

    public float getUsdt() {
        return usdt;
    }

    public void setUsdt(float usdt) {
        this.usdt = usdt;
    }

    public float getXrp() {
        return xrp;
    }

    public void setXrp(float xrp) {
        this.xrp = xrp;
    }

    public float getBch() {
        return bch;
    }

    public void setBch(float bch) {
        this.bch = bch;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
