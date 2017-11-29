package cn.nicky.crab.service.impl;

import cn.nicky.crab.model.po.VoucherCategory;
import cn.nicky.crab.repository.VoucherCategoryRepository;
import cn.nicky.crab.repository.VoucherRepository;
import cn.nicky.crab.service.IVoucherCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhang on 2017/11/13.
 */
@Service
public class VoucherCategoryService implements IVoucherCategoryService {
    @Autowired
    private VoucherCategoryRepository voucherCategoryRepository;

    @Override
    public List<VoucherCategory> getVoucherCategories(){
        return voucherCategoryRepository.findAll();
    }
}
