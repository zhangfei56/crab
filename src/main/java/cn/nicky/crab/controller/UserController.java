package cn.nicky.crab.controller;

import cn.nicky.crab.model.po.User;
import cn.nicky.crab.security.SecurityUser;
import cn.nicky.crab.service.impl.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.persistence.OneToOne;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

    @Resource
    private UserService sUserService;

    @RequestMapping(value = "/admin/login")
    public String adminLogin(){
        return "admin/login";
    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String findAllUser(Model model){
        List<User> users = sUserService.findAllUser();
        model.addAttribute("users", users);
        return "admin/userList";
    }

    @RequestMapping(value = "/admin/addUser", method = RequestMethod.GET)
    public String addUser(Model model){

        return "admin/addUser";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    @RequestMapping(value = "/admin/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute User user){
        System.out.print("user.phoneNumber"+user.getPhoneNumber());
        sUserService.addUser(user);
        return "success";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/admin/home")
    public String adminHome(){
        return "admin/home";
    }

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
    public String findUser() {
        SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return "client/user";
    }

    @RequestMapping("/client/action")
    public String action() {

        return "client/action";
    }

    @RequestMapping(value="/client/json/user/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public String updatePassword(String oldPassword, String newPassword){
        SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!sUserService.checkPassword(securityUser.getUsername(), oldPassword)){{
            return "oldPasswordFailed";
        }}
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
