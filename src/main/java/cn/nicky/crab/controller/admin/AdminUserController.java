package cn.nicky.crab.controller.admin;

import cn.nicky.crab.model.po.User;
import cn.nicky.crab.service.impl.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class AdminUserController {

    @Resource
    private UserService sUserService;

    @RequestMapping(value = "/admin/login")
    public String adminLogin(){
        return "admin/login";
    }

    @RequestMapping(value = "/admin")
    public String admin(){
        return "admin/login";
    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String findAllUser(Model model, Pageable pageable){
        Page<User> users = sUserService.findAllUser(pageable);
        model.addAttribute("datas", users);
        return "admin/userList";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/admin/addUser", method = RequestMethod.GET)
    public String addUser(){
        return "admin/addUser";
    }

    @ResponseBody
    @RequestMapping(value = "/admin/addUser", method = RequestMethod.POST)
    public String addUser(User user){
        sUserService.addUser(user);
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/admin/user/phone", method = RequestMethod.GET)
    public String chenkPhoneNumber(String phone_number){
        String ss=sUserService.checkPhoneNumber(phone_number).toString();
        return ss;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/admin/home")
    public String adminHome(){
        return "admin/home";
    }

}
