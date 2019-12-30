package com.project.currency.controlers;

import com.project.currency.models.HistoryModel;
import com.project.currency.services.HistoryMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class HistoryController {
    @Autowired
    private HistoryMeasureService service;

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public String showHistory(Model model){

        List<HistoryModel> history = service.getAllHistory();

        model.addAttribute("history", history);

        return "history";


    }
}
