package com.project.currency.controlers;

import com.project.currency.arbs.Exchange;
import com.project.currency.arbs.Processor;
import com.project.currency.arbs.Scanner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Controller
public class ArbsController {
    @GetMapping("/arbs")
    public String showArbs(){
        return "arbs";
    }
}
