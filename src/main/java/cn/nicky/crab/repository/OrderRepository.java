package cn.nicky.crab.repository;

import cn.nicky.crab.model.po.OrderForm;
import cn.nicky.crab.model.po.Voucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by zhang on 2017/11/14.
 */
@Repository
public interface OrderRepository extends JpaRepository <OrderForm, Integer>{

    Page<OrderForm> findByVoucher_UserId(int userId, Pageable pageable);




}
