package com.project.currency.controlers;

import com.project.currency.models.ChartObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String showIndex(Model model){
        model.addAttribute("chartData", new ChartObject());
        model.addAttribute("nomics", "");
        model.addAttribute("coinMarket", "");
        return "crypto";
    }
}
