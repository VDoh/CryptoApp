package com.project.currency.controlers;

import com.project.currency.models.User;
import com.project.currency.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/test")
public class TestControler {

    @Autowired
    UserService service;

    @GetMapping
    public List<User> getUsers(){
        return service.getUsers();
    }
}
