package cn.nicky.crab.repository;

import cn.nicky.crab.model.po.CourierCompany;
import cn.nicky.crab.model.po.OrderForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhang on 2017/11/14.
 */
@Repository
public interface CourierCompanyRepository extends JpaRepository <CourierCompany, Integer>{

    List<CourierCompany> findByCommon(boolean common);
}
