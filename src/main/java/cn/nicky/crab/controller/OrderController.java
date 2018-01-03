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
        String body="";

        httpResponse.setStatus(response.getStatusCode());

        try {
            body =  new String(response.getBody(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return body;
    }

    @RequestMapping(value = "/client/orders", method = RequestMethod.GET)
    public String getOrders(Model model, Integer status, Pageable pageable){
        SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Page<OrderForm> orderForms = orderService.findOrders(securityUser.getId(),status, pageable);
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

        return "client/voucher/voucherOrder";

    }

    @ResponseBody
    @RequestMapping(value = "/anyone/express/{voucherCode}")
    public String getExpressDetail(@PathVariable String voucherCode, HttpServletResponse httpResponse){
        String body = null;
        Voucher voucher = voucherService.findByIdentityCode(voucherCode);
        try {
        ApiResponse response = orderService.getCourier(voucher.getOrderForm().getCourierCompanyType(), voucher.getOrderForm().getTrackingNumber());
        httpResponse.setStatus(response.getStatusCode());


            body =  new String(response.getBody(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{\"status\":\"0\",\"msg\":\"ok\",\"result\":{\"number\":\"1202516745301\",\"type\":\"yunda\",\"list\":[{\"time\":\"2017-01-07 16:05:38\",\"status\":\"湖南省炎陵县公司快件已被 已签收 签收\"},{\"time\":\"2017-01-07 16:02:43\",\"status\":\"湖南省炎陵县公司快件已被 已签收 签收\"},{\"time\":\"2017-01-07 15:43:42\",\"status\":\"湖南省炎陵县公司进行派件扫描；派送业务员：陈晓东；联系电话：18173377752\"},{\"time\":\"2017-01-06 18:26:08\",\"status\":\"湖南长沙分拨中心从站点发出，本次转运目的地：湖南省炎陵县公司\"},{\"time\":\"2017-01-06 17:06:52\",\"status\":\"湖南长沙分拨中心在分拨中心进行卸车扫描\"},{\"time\":\"2017-01-05 23:48:08\",\"status\":\"浙江杭州分拨中心进行装车扫描，即将发往：湖南长沙分拨中心\"},{\"time\":\"2017-01-05 23:44:03\",\"status\":\"浙江杭州分拨中心进行中转集包扫描，将发往：湖南长沙分拨中心\"},{\"time\":\"2017-01-05 23:35:40\",\"status\":\"浙江杭州分拨中心在分拨中心进行称重扫描\"},{\"time\":\"2017-01-05 20:01:03\",\"status\":\"浙江主城区公司杭州拱墅区祥符桥服务部进行揽件扫描\"}],\"deliverystatus\":\"3\",\"issign\":\"1\"}}";
    }
}
