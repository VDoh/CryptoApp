package com.project.currency.services;

import com.project.currency.models.User;
import com.project.currency.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> getUsers(){
        return (List<User>) repo.findAll();
    }

    public User getUser(Long id){
        User someUser = new User();
        Optional<User> user = repo.findById(id);
        return user.get();
    }

    private static String[] parseKeyValue(String token) {
        if (token == null || token.equals("")) {
            return null;
        }

        token = token.trim();

        int index = token.indexOf("=");

        if (index == -1) {
            return new String[] { token.trim(), "" };
        } else {
            return new String[] { token.substring(0, index).trim(),
                    token.substring(index + 1).trim() };
        }

    }

    public void makeUserChange(Long id, String ... changes){

        Properties properties = new Properties();

        for (String param : changes) {
            String[] pair = parseKeyValue(param);

            if (pair != null) {
                properties.setProperty(pair[0], pair[1]);
            }

        }

    }

}

