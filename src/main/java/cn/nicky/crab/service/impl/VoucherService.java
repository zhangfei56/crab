package cn.nicky.crab.service.impl;

import cn.nicky.crab.model.po.CourierCompany;
import cn.nicky.crab.model.po.Template;
import cn.nicky.crab.model.po.Voucher;
import cn.nicky.crab.repository.CourierCompanyRepository;
import cn.nicky.crab.repository.TemplateRepository;
import cn.nicky.crab.repository.VoucherCategoryRepository;
import cn.nicky.crab.repository.VoucherRepository;
import cn.nicky.crab.service.IVoucherService;
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
            v.setIdentityCode(generateIdentityCode(10));
            v.setVoucherCategory(voucherCategoryRepository.findOne(categoryId));
            v.setStatus(VoucherStatusConstant.CREATEED.ordinal());
            vouchers.add(v);
        }
        voucherRepository.save(vouchers);
        return true;
    }


    private String generateIdentityCode(int length){
        StringBuilder sb=new StringBuilder();
        Random rand=new Random();//随机用以下三个随机生成器
        Random randdata=new Random();
        int data=0;
        for(int i=0;i<length;i++)
        {
            int index=rand.nextInt(3);
            //目的是随机选择生成数字，大小写字母
            switch(index)
            {
                case 0:
                    data=randdata.nextInt(10);//仅仅会生成0~9
                    sb.append(data);
                    break;
                case 1:
                    data=randdata.nextInt(26)+65;//保证只会产生65~90之间的整数
                    sb.append((char)data);
                    break;
                case 2:
                    data=randdata.nextInt(26)+97;//保证只会产生97~122之间的整数
                    sb.append((char)data);
                    break;
            }
        }
        return sb.toString();
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
