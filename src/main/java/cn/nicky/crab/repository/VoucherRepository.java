package cn.nicky.crab.repository;

import cn.nicky.crab.model.po.User;
import cn.nicky.crab.model.po.Voucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhang on 2017/11/14.
 */
@Repository
public interface VoucherRepository extends JpaRepository <Voucher, Integer>{

    Page<Voucher> findByUserId(Integer userId, Pageable pageable);

    Page<Voucher> findByUserIdAndStatus(Integer userId, Integer status, Pageable pageable);

    Page<Voucher> findByUserIdOrderByCreateDateTimeDesc(Integer userId, Pageable pageable);

    Page<Voucher> findByUserIdAndStatusOrderByCreateDateTimeDesc(Integer userId,Integer status, Pageable pageable);

    Voucher findByIdentityCode(String code);

    @Modifying
    @Transactional
    @Query(value = "update voucher v set v.status = ?1 where v.identity_code = ?2", nativeQuery = true)
    int updateStatus(int status, String code);

    int countByUserIdAndStatus(Integer userId, Integer status);

}
