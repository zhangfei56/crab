package cn.nicky.crab.controller.admin;

import cn.nicky.crab.model.po.VoucherCategory;
import cn.nicky.crab.service.impl.VoucherCategoryService;
import com.alibaba.fastjson.JSON;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class AdminVoucherCategoryController {

    @Resource
    private VoucherCategoryService voucherCategoryService;

    @ResponseBody
    @RequestMapping(value = "/admin/voucher/category", method = RequestMethod.POST)
    public String addVoucherCategory(VoucherCategory category){
        return voucherCategoryService.addVoucherCategory(category);
    }

    @RequestMapping(value = "/admin/voucher/categories", method = RequestMethod.GET)
    public String findVoucherCategories(Model model){
        List<VoucherCategory> categories = voucherCategoryService.getVoucherCategories();
        model.addAttribute("categories", categories);
        return "admin/voucherCategoryList";
    }

    @ResponseBody
    @RequestMapping(value = "/admin/voucher/category", method = RequestMethod.DELETE)
    public String addVoucherCategory(int categoryId ){
        voucherCategoryService.deleteVoucherCategory(categoryId);
        return "success";
    }
}
