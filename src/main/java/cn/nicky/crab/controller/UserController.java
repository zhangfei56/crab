package cn.nicky.crab.controller;

import cn.nicky.crab.model.po.User;
import cn.nicky.crab.security.SecurityUser;
import cn.nicky.crab.service.impl.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.DateUtils;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class UserController {

    @Resource
    private UserService sUserService;

    @RequestMapping("/login")
    public String login(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return "client/index";
        }else {
            return "client/login";
        }
    }

    @RequestMapping("/")
    public String root() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return "client/index";
        }else {
            return "welcome";
        }
    }

    @RequestMapping("/client/user")
    public String findUser(Model model) {
        SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = sUserService.findUserByPhoneNumber(securityUser.getUsername());
        model.addAttribute("user", user);

        return "client/user";
    }



    @RequestMapping("/client/action")
    public String action() {

        return "client/action";
    }

    @RequestMapping(value="/client/json/user/checkPassword", method = RequestMethod.GET)
    @ResponseBody
    public String checkPassword(String oldPassword){
        SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return sUserService.checkPassword(securityUser.getUsername(), oldPassword).toString();
    }

    @RequestMapping(value="/client/json/message/users", method = RequestMethod.GET)
    @ResponseBody
    public String getMessageUsers(){

        return JSON.toJSONString(sUserService.getMessageUsers());
    }

    @RequestMapping(value="/client/json/user/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public String updatePassword(String newPassword){
        SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        sUserService.updatePassword(newPassword, securityUser.getUsername());
        return "success";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        Object securityUser =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean isNotMember = securityUser.toString().equals("anonymousUser");
        model.addAttribute("isNotMember", isNotMember);
        return "client/index";
    }

    @RequestMapping("/403")
    public String error(){
        return "403";
    }
}
