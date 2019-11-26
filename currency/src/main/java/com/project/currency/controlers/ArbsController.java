package com.project.currency.controlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ArbsController {
    @GetMapping("/arbs")
    public String showHistory(){
        return "arbs";
    }
}
