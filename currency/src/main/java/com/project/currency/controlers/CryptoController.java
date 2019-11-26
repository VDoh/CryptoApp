package com.project.currency.controlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CryptoController {
    @GetMapping("/crypto")
    public String showCrypto(){
        return "crypto";
    }
}
