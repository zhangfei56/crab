package cn.nicky.crab.controller;

import cn.nicky.crab.service.impl.UserService;
import cn.nicky.crab.util.QRCodeFactory;
import com.google.zxing.WriterException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

@Controller
public class UserController {

    @Resource
    private UserService sUserService;

    @RequestMapping("/home")
    public String home() {
        return "home";

    }

    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/admin/login")
    public String adminLogin(){
        return "admin/login";
    }

    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/admin/home")
    public String adminHome(){
        return "admin/home";
    }

    @RequestMapping("/hello")
    public String hello() {

        return "hello";

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

        return "login";
    }

    @RequestMapping("/")
    public String root() {
        return "index";

    }

    @RequestMapping("/403")
    public String error(){
        return "403";
    }
}
