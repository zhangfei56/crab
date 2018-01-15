package cn.nicky.crab.service.impl;

import cn.nicky.crab.model.po.Role;
import cn.nicky.crab.model.po.Template;
import cn.nicky.crab.model.po.User;
import cn.nicky.crab.model.po.Voucher;
import cn.nicky.crab.model.vo.VUser;
import cn.nicky.crab.repository.RoleRepository;
import cn.nicky.crab.repository.TemplateRepository;
import cn.nicky.crab.repository.UserRepository;
import cn.nicky.crab.service.IUserService;
import cn.nicky.crab.util.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Resource
    private TemplateRepository templateRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public User findUserByPhoneNumber(String phone) {
        logger.debug("begin");
        return userRepository.findUserByPhoneNumber(phone);
    }

    public Page<User> findAllUser(Pageable pageable) {
        PageRequest pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), new Sort(Sort.Direction.DESC, "registerDate"));
        Page<User> users = userRepository.findAll(pageRequest);
        return users;
    }

    @Override
    public void addUser(User user) {
        user.setPassword(new Md5PasswordEncoder().encodePassword(user.getPassword(), AppConstants.MD5_PASSWORD_ENCODER_SALT));
        user.setRegisterDate(new Date());
        Role role = roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<Role>();
        roles.add(role);
        user.setRoles(roles);
        User result = userRepository.save(user);
        Template template = new Template();
        template.setUserId(result.getId());
        template.setPhoneNumber(result.getPhoneNumber());
        template.setContact(result.getName());
        template.setCompanyName("阳澄湖大闸蟹集团");
        templateRepository.save(template);
    }

    public Boolean checkPhoneNumber(String phoneNumber){
        User user = userRepository.findUserByPhoneNumber(phoneNumber);

        return user==null?true:false;
    }

    public Boolean checkPassword(String phoneNumber, String password){
        User user = userRepository.findUserByPhoneNumber(phoneNumber);

        return new Md5PasswordEncoder().encodePassword(password, AppConstants.MD5_PASSWORD_ENCODER_SALT)
                .equals(user.getPassword());
    }

    public void updatePassword(String newPassword, String phoneNumber){
        userRepository.updatePassword(new Md5PasswordEncoder().encodePassword(newPassword, AppConstants.MD5_PASSWORD_ENCODER_SALT), phoneNumber);
    }

    public boolean forgetPassword(String phoneNumber){
        User user = userRepository.findUserByPhoneNumber(phoneNumber);
        if(user == null) return false;
        String newPassword = VoucherService.generateIdentityCode(4);
        String message = "您的新密码是："+ newPassword+". 请及时更新密码";
        // TODO
        updatePassword(newPassword, phoneNumber);
        return true;
    }

    public List<VUser> getMessageUsers(){
        List<User> users = userRepository.findAll();
        List<VUser> vUsers = new ArrayList<VUser>();
        for (int i = 0; i < users.size(); i++) {
            VUser user = new VUser();
            user.setId(users.get(i).getId());
            user.setName(users.get(i).getName());
            vUsers.add(user);
        }
        return vUsers;

    }
}
