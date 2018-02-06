package cn.nicky.crab.controller.admin;

import cn.nicky.crab.model.po.InvitationCode;
import cn.nicky.crab.model.po.User;
import cn.nicky.crab.security.SecurityUser;
import cn.nicky.crab.service.impl.InviteService;
import cn.nicky.crab.service.impl.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@PreAuthorize("hasRole('ADMIN')")
public class AdminInviteController {

    @Resource
    private InviteService inviteService;


    @RequestMapping(value = "/admin/invite", method = RequestMethod.GET)
    public String getInvite(Boolean markForDelete, Pageable pageable, Model model){
        Page<InvitationCode> invitationCodes = inviteService.getInvitationCode(markForDelete, pageable);

        model.addAttribute("datas", invitationCodes);

        return "admin/inviteList";
    }

    @ResponseBody
    @RequestMapping(value = "/admin/invite/add", method = RequestMethod.GET)
    public String addInvite(){
        SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        inviteService.addInvitationCode(securityUser.getId());
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/admin/invite/destroy", method = RequestMethod.GET)
    public String destroyInvite(int inviteId){

        inviteService.destroyInvitationCode(inviteId);
        return "success";
    }

}
