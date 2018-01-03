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

    @RequestMapping(value="/client/updateUser", method = RequestMethod.POST)
    @ResponseBody
    public String updateUser(@RequestParam(value = "file") MultipartFile file){
        if (file.isEmpty()) {
            return "failed";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 文件上传后的路径
        String filePath = "E://test//";
        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            return "success";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "failed";

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
