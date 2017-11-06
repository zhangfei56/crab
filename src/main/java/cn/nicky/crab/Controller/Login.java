package cn.nicky.crab.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Login {

    @RequestMapping("/login")
    public String login(){

        return "login";
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }
}
