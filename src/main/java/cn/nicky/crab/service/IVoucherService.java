package cn.nicky.crab.service;

import cn.nicky.crab.model.po.Voucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by zhang on 2017/11/13.
 */
public interface IVoucherService {
    Page<Voucher> findByUserId(Integer userId, Pageable pageable, Integer status);
}
