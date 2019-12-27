package com.project.currency.models;

import javax.persistence.*;

@Entity
@Table(name = "vip_application")
public class VipApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_app;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "id_user", nullable = false, insertable = false, updatable = false )
    private User user;

    private long id_user;
    String text_content;
    String date;
    int status;

    public long getId_app() {
        return id_app;
    }

    public void setId_app(long id_app) {
        this.id_app = id_app;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public String getText_content() {
        return text_content;
    }

    public void setText_content(String text_content) {
        this.text_content = text_content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
