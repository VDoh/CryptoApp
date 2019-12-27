package com.project.currency.controlers;

import com.project.currency.arbs.Data;
import com.project.currency.models.ArbModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;


@Controller
public class ArbsController {
    @GetMapping("/arbs")
    public String showArbs(Model model){
//        model.addAttribute("list", getArbsList());
        return "arbs";
    }

    @RequestMapping(value = "/updatearbs", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<ArbModel> getArbsList () {
        return  Data.getInstance().getArbsList();
    }
}
