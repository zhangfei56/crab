package cn.nicky.crab.service.impl;

import cn.nicky.crab.model.po.User;
import cn.nicky.crab.repository.UserRepository;
import cn.nicky.crab.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public User findUserByPhoneNumber(String phone) {
        logger.debug("begin");
        return userRepository.findUserByPhone(phone);
    }

    @Override
    public List<User> findAllUser() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }


}
