package com.project.currency.controlers;

import com.project.currency.models.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    @RequestMapping( value = "/user", method = RequestMethod.GET)
    public String getStartContent() {
        return "userLoginPanel";
    }

    //reqmapping jako arg value = "path"
    @RequestMapping( value = "/user", method = RequestMethod.POST)
    public String login(@ModelAttribute(name = "loginForm") LoginForm loginForm, Model model) {
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();

        if ("admin".equals(username) && "admin".equals(password)) {
            return "redirect:/worked";
        }

        model.addAttribute("invalidCredentials", true);
        return "userLoginPanel";
    }

    @RequestMapping( value = "/worked", method = RequestMethod.GET)
    public String getWorkedContent() {
        return "worked";
    }
}

