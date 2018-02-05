package cn.nicky.crab.controller.admin;

import cn.nicky.crab.model.po.User;
import cn.nicky.crab.service.impl.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class AdminInviteController {

    @Resource
    private UserService sUserService;


    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/admin/invite", method = RequestMethod.GET)
    public String getInvite(Boolean markForDelete, Pageable pageable, Model model){

        return "admin/inviteList";
    }

    @ResponseBody
    @RequestMapping(value = "/admin/addUser", method = RequestMethod.POST)
    public String addUser(User user){
        sUserService.addUser(user);
        return "success";
    }

}
