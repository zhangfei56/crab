package cn.nicky.crab.service;

import cn.nicky.crab.model.po.User;

import java.util.List;

/**
 * Created by zhang on 2017/11/11.
 */
public interface IUserService {
    User findUserByPhoneNumber(String email);

    List<User> findAllUser();

    void addUser(User user);
}
