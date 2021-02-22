package trius.springframework.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import trius.springframework.repositories.UserRepository;
import trius.springframework.security.User;
import trius.springframework.services.UserServiceImpl;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/")
    public String home(){
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage(Model model) {
        model.addAttribute("user", new User());

        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String saveRegisterPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, Map<String, Object> map) {

        model.addAttribute("user", user);

        if (result.hasErrors()) {
            return "register";
        } else {

            String pwd = user.getPassword();
            String encryptPwd = passwordEncoder.encode(pwd);
            user.setPassword(encryptPwd);
            map.put("message", "Successful");
            userService.saveUser(user);
        }
        return "register";
    }
}
