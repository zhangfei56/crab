package cn.nicky.crab.controller.admin;

import cn.nicky.crab.model.po.CourierCompany;
import cn.nicky.crab.model.po.VoucherCategory;
import cn.nicky.crab.service.impl.OrderService;
import cn.nicky.crab.service.impl.VoucherCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class AdminExpressCompanyController {

    @Resource
    private OrderService orderService;

    @RequestMapping(value = "/admin/express/companies", method = RequestMethod.GET)
    public String findExpressCompany(Model model){
        List<CourierCompany> courierCompanies = orderService.findCourierCompany(false);
        model.addAttribute("companies",  courierCompanies);

        return "admin/expressCompanyList";
    }

    @ResponseBody
    @RequestMapping(value = "/admin/express/company/status", method = RequestMethod.GET)
    public String changeExpressCompanyStatus(int companyId){
        orderService.changeCourierCompanyStatus(companyId);
        return "success";
    }

}
