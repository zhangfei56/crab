package cn.nicky.crab.repository;

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
public interface UserRepository extends JpaRepository <User, Integer>{

    @Query(value = "select * from User u where u.phone_number=:phone", nativeQuery = true)
    User findUserByPhone(@Param("phone") String phone);

    @Query(value = "select * from User u order by :order limit :limit", nativeQuery = true)
    List<User> findUserByQuery(@Param("limit")String limit, @Param("order")String order);
}
