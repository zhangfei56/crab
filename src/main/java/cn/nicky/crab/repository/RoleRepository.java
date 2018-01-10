package cn.nicky.crab.repository;

import cn.nicky.crab.model.po.CourierCompany;
import cn.nicky.crab.model.po.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhang on 2017/11/14.
 */
@Repository
public interface RoleRepository extends JpaRepository <Role, Integer>{

    Role findByName(String name);
}
