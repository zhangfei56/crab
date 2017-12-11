package cn.nicky.crab.repository;

import cn.nicky.crab.model.po.Template;
import cn.nicky.crab.model.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhang on 2017/11/14.
 */
@Component
public interface TemplateRepository extends JpaRepository <Template, Integer>{
    Template findTemplateByUserId(Integer userId);

}
