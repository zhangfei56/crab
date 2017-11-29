package cn.nicky.crab.service;

import cn.nicky.crab.model.po.VoucherCategory;

import java.util.List;

/**
 * Created by zhang on 2017/11/13.
 */
public interface IVoucherCategoryService {
    List<VoucherCategory> getVoucherCategories();
}
