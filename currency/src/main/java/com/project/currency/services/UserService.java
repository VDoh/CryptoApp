package com.project.currency.services;

import com.project.currency.models.User;
import com.project.currency.repositories.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.expression.ExpressionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserService {

    @Autowired
    private UserRepository repo;
    private JdbcTemplate jdbcTemplate;

    public List<User> getUsers(){
        return (List<User>) repo.findAll();
    }

    public User getUser(Long id){
        User someUser = new User();
        Optional<User> user = repo.findById(id);
        return user.get();
    }

    public void makeUserChange(Long id, String user_type, String nick, String email, String delAcc){
        User getUser = this.getUser(id);

        if(delAcc.equals("yes")){
            repo.delete(getUser);
            return;
        }
        if((user_type=="") == false){
            Integer type = Integer.parseInt(user_type);
            getUser.setUser_type(type);
        }
        if(!(nick=="")){
            getUser.setNick(nick);
        }
        if(!(email=="")){
            getUser.setEmail(email);
        }
        repo.save(getUser);
    }
    public void createUser(Integer user_type, String nick, String email, String password){
        User newUser = new User();

        newUser.setUser_type(user_type);
        newUser.setNick(nick);
        newUser.setEmail(email);

        String hashedPassword = DigestUtils.md5Hex(password).toUpperCase();
        newUser.setPassword(hashedPassword);

        repo.save(newUser);
    }
}

