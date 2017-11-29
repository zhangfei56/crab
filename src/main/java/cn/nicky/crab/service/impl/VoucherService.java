package cn.nicky.crab.service.impl;

import cn.nicky.crab.model.po.Voucher;
import cn.nicky.crab.repository.VoucherRepository;
import cn.nicky.crab.service.IVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhang on 2017/11/13.
 */
@Service
public class VoucherService implements IVoucherService {

    @Autowired
    private VoucherRepository voucherRepository;

    @Override
    public Page<Voucher> findByUserId(Integer userId, Pageable pageable) {
        Page<Voucher> v = voucherRepository.findByUserId(userId, pageable);

        return v;
    }
}
