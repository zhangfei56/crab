package cn.nicky.crab.controller;

import cn.nicky.crab.model.po.OrderForm;
import cn.nicky.crab.model.po.Template;
import cn.nicky.crab.model.po.Voucher;
import cn.nicky.crab.security.SecurityUser;
import cn.nicky.crab.service.impl.OrderService;
import cn.nicky.crab.service.impl.VoucherService;
import com.alibaba.cloudapi.sdk.core.model.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
public class OrderController {

    @Resource
    private OrderService orderService;

    @Resource
    private VoucherService voucherService;
    @ResponseBody
    @RequestMapping(value = "/client/order/courierCompanies", method = RequestMethod.GET)
    public String findCourierCompanies(Model model, HttpServletResponse httpResponse){
        ApiResponse response = orderService.getCourierCompany();
        String body=null;

        httpResponse.setStatus(response.getStatusCode());

        try {
            body =  new String(response.getBody(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return body;
    }


    public String getOrders(Model model,Integer status, Pageable pageable){
        SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Page<OrderForm> orderForms = orderService.findOrders(securityUser.getId(), pageable);
        model.addAttribute("orders", orderForms);
        model.addAttribute("status", status);
        return "/client/orderList";
    }

    @ResponseBody
    @RequestMapping(value = "/anyone/order", method = RequestMethod.POST)
    public String addOrder(OrderForm orderForm){

        if(!voucherService.checkVoucherNotOrder(orderForm.getVoucher().getIdentityCode()))
            return "error";
        orderService.addOrder(orderForm);
        return "success";
    }

    @RequestMapping(value = "/anyone/voucher/order/{voucherCode}")
    public String showVoucherOrderDetail(Model model, @PathVariable String voucherCode){

        Voucher voucher = voucherService.findByIdentityCode(voucherCode);
        if(voucher == null){
            return "client/404";
        }
        model.addAttribute("voucher", voucher);

        if(voucher.getStatus() != 2 ){
            return "client/voucher/voucherOrder";
        }else{

            return "client/voucher/courier";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/anyone/express/{voucherCode}")
    public String getExpressDetail(@PathVariable String voucherCode){
        String body = null;
        Voucher voucher = voucherService.findByIdentityCode(voucherCode);

        ApiResponse response = orderService.getCourier(voucher.getOrderForm().getCourierCompanyType(), voucher.getOrderForm().getTrackingNumber());
        try {
            body =  new String(response.getBody(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return body;
    }
}
