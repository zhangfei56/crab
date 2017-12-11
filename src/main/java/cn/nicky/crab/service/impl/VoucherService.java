package cn.nicky.crab.service.impl;

import cn.nicky.crab.model.po.Voucher;
import cn.nicky.crab.repository.VoucherCategoryRepository;
import cn.nicky.crab.repository.VoucherRepository;
import cn.nicky.crab.service.IVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by zhang on 2017/11/13.
 */

@Service
public class VoucherService implements IVoucherService {

    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private VoucherCategoryRepository voucherCategoryRepository;

    public enum VoucherStatusConstant {
        CREATEED,
        ORDERED,
        FINISHED
    }


    @Override
    public Page<Voucher> findByUserId(Integer userId, Pageable pageable) {
        Page<Voucher> v = null;
        if(pageable.getSort() == null){
            v = voucherRepository.findByUserIdOrderByCreateDateTimeDesc(userId, pageable);
        }else {
            v = voucherRepository.findByUserId(userId, pageable);
        }

        return v;
    }

    public boolean addVoucher(Integer userId, Integer total, Integer categoryId, Date deadlineDate){
        List<Voucher> vouchers = new ArrayList<Voucher>();
        for(int i=0; i<total; i++){
            Voucher v = new Voucher();
            v.setUserId(userId);
            v.setActiveDateTime(deadlineDate);
            v.setCreateDateTime(new Date());
            v.setIdentityCode(generateIdentityCode());
            v.setVoucherCategory(voucherCategoryRepository.findOne(categoryId));
            v.setStatus(VoucherStatusConstant.CREATEED.ordinal());
            vouchers.add(v);
        }
        voucherRepository.save(vouchers);
        return true;
    }

    private String generateIdentityCode(){
        String code;
        while (true){
            code = UUID.randomUUID().toString();
            code = DEKHash(code) +"";

            Voucher voucher = voucherRepository.findByIdentityCode(code);
            if(voucher==null){
                break;
            }
        }

        return code;
    }

    private static int DEKHash(String str)
    {
        int hash = str.length();
        for (int i = 0; i < str.length(); i++)
        {
            hash = ((hash << 5) ^ (hash >> 27)) ^ str.charAt(i);
        }
        return (hash & 0x7FFFFFFF);
    }
}
