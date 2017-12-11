package cn.nicky.crab.repository;

import cn.nicky.crab.model.po.User;
import cn.nicky.crab.model.po.Voucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhang on 2017/11/14.
 */
@Repository
public interface VoucherRepository extends JpaRepository <Voucher, Integer>{

    Page<Voucher> findByUserId(Integer userId, Pageable pageable);

    Page<Voucher> findByUserIdOrderByCreateDateTimeDesc(Integer userId, Pageable pageable);


    Voucher findByIdentityCode(String code);

}
