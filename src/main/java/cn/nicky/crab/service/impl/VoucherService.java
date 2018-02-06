package cn.nicky.crab.service.impl;

import cn.nicky.crab.model.po.Template;
import cn.nicky.crab.model.po.Voucher;
import cn.nicky.crab.repository.TemplateRepository;
import cn.nicky.crab.repository.VoucherCategoryRepository;
import cn.nicky.crab.repository.VoucherRepository;
import cn.nicky.crab.service.IVoucherService;
import cn.nicky.crab.util.IndentityCodeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zhang on 2017/11/13.
 */

@Service
public class VoucherService implements IVoucherService {

    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private VoucherCategoryRepository voucherCategoryRepository;



    public enum VoucherStatusConstant {
        CREATEED,
        ORDERED,
        RECEIVE,
        FINISHED
    }


    @Override
    public Page<Voucher> findByUserId(Integer userId, Pageable pageable, Integer status) {
        Page<Voucher> v;
        if(pageable.getSort() == null){
            if(status == -1){
               // voucherRepository.findByUserIdOrderByCreateDateTimeDesc(userId, pageable);
                v = voucherRepository.findByUserIdOrderByCreateDateTimeDesc(userId, pageable);
            }else {
                v = voucherRepository.findByUserIdAndStatusOrderByCreateDateTimeDesc(userId,status, pageable);
            }
        }else {
            if(status==null){
                v = voucherRepository.findByUserId(userId, pageable);
            }else {
                v = voucherRepository.findByUserIdAndStatus(userId,status, pageable);

            }
        }

        return v;
    }

    public Template findTemplate(Integer userId){
        return templateRepository.findTemplateByUserId(userId);
    }

    public boolean addVoucher(Integer userId, Integer total, Integer categoryId, Date deadlineDate){
        List<Voucher> vouchers = new ArrayList<Voucher>();
        for(int i=0; i<total; i++){
            Voucher v = new Voucher();
            v.setUserId(userId);
            v.setActiveDateTime(deadlineDate);
            v.setCreateDateTime(new Date());
            v.setIdentityCode(IndentityCodeHelper.generateIdentityCode(10));
            v.setVoucherCategory(voucherCategoryRepository.findOne(categoryId));
            v.setStatus(VoucherStatusConstant.CREATEED.ordinal());
            vouchers.add(v);
        }
        voucherRepository.save(vouchers);
        return true;
    }

    public void updateTemplate(Template template){
        Template exist = templateRepository.findOne(template.getId());
        template.setUserId(exist.getUserId());
        templateRepository.save(template);
    }

    public Voucher findByIdentityCode(String code){
        return voucherRepository.findByIdentityCode(code);
    }

    public boolean checkVoucherNotOrder(String code){
        Voucher voucher = voucherRepository.findByIdentityCode(code);
        return voucher.getStatus()==0;
    }
}
