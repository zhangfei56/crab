package cn.nicky.crab.service;

import cn.nicky.crab.model.po.User;

/**
 * Created by zhang on 2017/11/11.
 */
public interface IUserService {
    User findUserByPhoneNumber(String email);
}
