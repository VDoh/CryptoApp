package com.project.currency.controlers;

import com.project.currency.dao.SQL;
import com.project.currency.models.LoginForm;
import com.project.currency.models.User;
import com.project.currency.models.UserChangeForm;
import com.project.currency.repositories.UserRepository;
import com.project.currency.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminControler {

    @Autowired
    UserService service;
    @Autowired
    UserRepository repo;

    @RequestMapping(method = RequestMethod.GET)
    public String getStartContent() {
        return "admin_login_panel";
    }
    //reqmapping jako arg value = "path"

    @RequestMapping(method = RequestMethod.POST)
    public String login(@ModelAttribute(name = "loginForm") LoginForm loginForm, Model model) {
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();

        String hashedPassword = DigestUtils.md5Hex(password).toUpperCase();
        System.out.println(hashedPassword);
        SQL sql = new SQL();

        String passwordQuery = sql.checkPassword(username);

        if (passwordQuery == null) {
            model.addAttribute("invalidCredentials", true);
            return "admin_login_panel";
        }

        if (passwordQuery.equals(hashedPassword)) {
            return "redirect:/admin/panel";
        }

        model.addAttribute("invalidCredentials", true);
        return "admin_login_panel";
    }

    @RequestMapping(value = "/panel", method = RequestMethod.GET)
    public String panelContent() {
        return "admin_panel";
    }

    @RequestMapping(value = "panel/manage", method = RequestMethod.GET)
    public String userList(Model model){

        List<User> list = service.getUsers();
        model.addAttribute("users", repo.findAll());
        return "admin_manage_users";
    }

    @RequestMapping(value = "panel/manage", params = {"id"} ,method = RequestMethod.GET)
    public String getUser(@RequestParam("id") Long id, Model model){

        User user = service.getUser(id);
        model.addAttribute("user",user);
        return "admin_manage_single_user";
    }

    @RequestMapping(value = "panel/manage", params = {"id"} ,method = RequestMethod.POST)
    public String makeUserChange(@RequestParam("id") Long id, @ModelAttribute(name="form") UserChangeForm form) {

        String user_type = form.getUser_type();
        String nick = form.getNick();
        String email = form.getEmail();
        String delAcc = form.getDelAcc();

        service.makeUserChange(id, user_type,nick, email, delAcc);

        return "redirect:/admin/panel";
    }
    @RequestMapping(value = "panel/create", method = RequestMethod.GET)
    public String loadUserCreationForm(){
        return "admin_create_user";
    }
    @RequestMapping(value = "panel/create", method = RequestMethod.POST)
    public String addUser(@ModelAttribute(name="form") User form){

        int user_type = form.getUser_type();
        String nick = form.getNick();
        String email = form.getEmail();
        String password = form.getPassword();

        service.createUser(user_type, nick,email,password);

        return "redirect:/admin/panel";
    }
}





