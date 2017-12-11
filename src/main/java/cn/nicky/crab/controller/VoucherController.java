package cn.nicky.crab.controller;

import cn.nicky.crab.model.po.Template;
import cn.nicky.crab.model.po.Voucher;
import cn.nicky.crab.security.SecurityUser;
import cn.nicky.crab.service.impl.VoucherService;
import org.hibernate.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/client")
public class VoucherController {

    @Resource
    private VoucherService voucherService;

    @RequestMapping(value = "/vouchers", method = RequestMethod.GET)
    public String findVouchers(Model model, Pageable pager){

        SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Page<Voucher> voucherPage = voucherService.findByUserId(securityUser.getId(), pager);
        model.addAttribute("vouchers", voucherPage);
        return "client/voucherList";
    }

    @RequestMapping(value = "/voucher/addVoucher", method = RequestMethod.GET)
    public String vouchers(Model model){
        return "client/addVoucher";
    }

    @ResponseBody
    @RequestMapping(value="/voucher/addVoucher", method = RequestMethod.POST)
    public String addVoucher(@RequestParam Integer total, @RequestParam Integer categoryId, @RequestParam Date deadlineDate) {
        SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        voucherService.addVoucher(securityUser.getId(), total, categoryId, deadlineDate);
        return "success";
    }

    @RequestMapping(value = "/voucher/template")
    public String showTemplate(Model model){
        SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Template template = voucherService.findTemplate(securityUser.getId());
        model.addAttribute("template", template);
        return "client/template";
    }


}
