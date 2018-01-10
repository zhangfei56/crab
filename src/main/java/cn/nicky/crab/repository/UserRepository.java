package cn.nicky.crab.repository;

import cn.nicky.crab.model.po.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhang on 2017/11/14.
 */
@Component
public interface UserRepository extends JpaRepository <User, Integer>{

    @Query(value = "select * from user u where u.phone_number=:phone", nativeQuery = true)
    User findUserByPhoneNumber(@Param("phone") String phone);

    @Query(value = "select * from user u order by :order limit :limit", nativeQuery = true)
    List<User> findUserByQuery(@Param("limit")String limit, @Param("order")String order);

    @Modifying
    @Transactional
    @Query(value = "update user u set u.password = ?1 where u.phoneNumber = ?2", nativeQuery = true)
    int updatePassword(String password, String phoneNumber);

}
