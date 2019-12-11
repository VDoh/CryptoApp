package com.project.currency.services;

import com.project.currency.models.User;
import com.project.currency.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    @Autowired
    UserRepository repo;

    public List<User> getUsers(){
        return (List<User>) repo.findAll();
    }

}
