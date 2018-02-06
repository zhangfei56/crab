package cn.nicky.crab.controller;

import cn.nicky.crab.model.po.User;
import cn.nicky.crab.security.SecurityUser;
import cn.nicky.crab.service.impl.InviteService;
import cn.nicky.crab.service.impl.UserService;
import com.alibaba.fastjson.JSON;
import com.aliyuncs.exceptions.ClientException;
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

    @Resource
    private InviteService inviteService;

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

    @RequestMapping(value = "/client/register", method = RequestMethod.GET)
    public String registerPage() {

        return "client/register";
    }

    @ResponseBody
    @RequestMapping(value = "/client/register", method = RequestMethod.POST)
    public String register(User user) {
        sUserService.addUser(user);
        return "success";
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

    @ResponseBody
    @RequestMapping(value = "/anyone/registerCode/send", method = RequestMethod.GET)
    public String sendRegisterCode(String phoneNumber) throws ClientException{
        sUserService.sendRegisterCodeToPhone(phoneNumber);

        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/anyone/check/phone", method = RequestMethod.GET)
    public String chenkPhoneNumber(String phone_number){
        String mark=sUserService.checkPhoneNumber(phone_number).toString();
        return mark;
    }

    @ResponseBody
    @RequestMapping(value = "/anyone/check/invite", method = RequestMethod.GET)
    public String chenkInviteCode(String invite_code){
        String mark=inviteService.checkInvitationCode(invite_code).toString();
        return mark;
    }

    @ResponseBody
    @RequestMapping(value = "/anyone/check/phoneVerify", method = RequestMethod.GET)
    public String chenkPhoneNumberVerify(String phoneNumber, String phone_number_verify){
        String mark = sUserService.checkRegisterCode(phoneNumber, phone_number_verify).toString();
        return mark;
    }

    @RequestMapping("/403")
    public String error(){
        return "403";
    }

    @RequestMapping("/404")
    public String notFound(){
        return "404";
    }
}
