package cn.nicky.crab.controller;

import cn.nicky.crab.model.po.Voucher;
import cn.nicky.crab.security.SecurityUser;
import cn.nicky.crab.service.impl.VoucherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class VoucherController {

    @Resource
    private VoucherService voucherService;

    @RequestMapping(value = "/client/vouchers", method = RequestMethod.GET)
    public String findVouchers(Model model, Pageable pager){
        SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Page<Voucher> voucherPage = voucherService.findByUserId(securityUser.getId(), pager);
        model.addAttribute("vouchers", voucherPage);
        return "client/voucherList";
    }

    @RequestMapping(value = "/client/voucher/addVoucher", method = RequestMethod.GET)
    public String addVoucher(Model model){
        return "client/addVoucher";
    }
}
