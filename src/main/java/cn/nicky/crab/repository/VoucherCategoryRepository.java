package cn.nicky.crab.repository;

import cn.nicky.crab.model.po.Voucher;
import cn.nicky.crab.model.po.VoucherCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by zhang on 2017/11/14.
 */
@Repository
public interface VoucherCategoryRepository extends JpaRepository <VoucherCategory, Integer>{

}
