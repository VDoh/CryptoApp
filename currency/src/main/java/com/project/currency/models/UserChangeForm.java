package com.project.currency.models;

import java.security.PublicKey;

public class UserChangeForm {

    private String user_type;
    private String nick;
    private String email;
    private String delAcc;

    public UserChangeForm(){
        super();
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getDelAcc() {
        return delAcc;
    }

    public void setDelAcc(String delAcc) {
        this.delAcc = delAcc;
    }
}
