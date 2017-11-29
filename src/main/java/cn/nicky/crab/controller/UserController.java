package cn.nicky.crab.controller;

import cn.nicky.crab.model.po.User;
import cn.nicky.crab.service.impl.UserService;
import cn.nicky.crab.util.QRCodeFactory;
import com.google.zxing.WriterException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
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
//        String content="大家好，我是李庆文，很高兴认识大家";
//        String logUri="e:\\test.png";
//        String outFileUri="new.png";
//        int[]  size=new int[]{430,430};
//        String format = "png";
//
//        try {
//            new QRCodeFactory().CreatQrImage(content, format, outFileUri, logUri,size);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (WriterException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
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

    @RequestMapping("/index")
    public String index() {
        return "client/index";
    }

    @RequestMapping("/403")
    public String error(){
        return "403";
    }
}
