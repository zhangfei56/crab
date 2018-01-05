package cn.nicky.crab.service.impl;

import cn.nicky.crab.model.po.Template;
import cn.nicky.crab.model.po.User;
import cn.nicky.crab.repository.TemplateRepository;
import cn.nicky.crab.repository.UserRepository;
import cn.nicky.crab.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Resource
    private TemplateRepository templateRepository;

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
        User result = userRepository.save(user);
        Template template = new Template();
        template.setUserId(result.getId());
        template.setPhoneNumber(result.getPhoneNumber());
        template.setContact(result.getName());
        template.setCompanyName("阳澄湖大闸蟹集团");
        templateRepository.save(template);
    }




}
