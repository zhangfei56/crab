package cn.nicky.crab.controller;

import cn.nicky.crab.model.po.VoucherCategory;
import cn.nicky.crab.service.impl.VoucherCategoryService;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class VoucherCategoryController {

    @Resource
    private VoucherCategoryService voucherCategoryService;

    @ResponseBody
    @RequestMapping(value = "/client/voucherCategory", method = RequestMethod.GET)
    public String findVoucherCategory(Model model){
        List<VoucherCategory> categories = voucherCategoryService.getVoucherCategories();
        return JSON.toJSONString(categories);
    }
}
