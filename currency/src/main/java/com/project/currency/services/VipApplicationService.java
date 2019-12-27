package com.project.currency.services;

import com.project.currency.models.VipApplication;
import com.project.currency.repositories.VipApplicationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VipApplicationService {

    @Autowired
    VipApplicationRepo repo;

    public List<VipApplication> apps(){
        return repo.find();
    }
}
