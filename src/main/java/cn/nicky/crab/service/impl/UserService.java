package cn.nicky.crab.service.impl;

import cn.nicky.crab.model.po.*;
import cn.nicky.crab.model.vo.VUser;
import cn.nicky.crab.repository.*;
import cn.nicky.crab.service.IUserService;
import cn.nicky.crab.util.AppConstants;
import cn.nicky.crab.util.IndentityCodeHelper;
import cn.nicky.crab.util.MsgManager;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
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
import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Resource
    private TemplateRepository templateRepository;

    @Resource
    private RegisterCodeRepository registerCodeRepository;

    @Autowired
    private InviteRepository inviteRepository;

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

    public void addUser(User user) {
        user.setPassword(new Md5PasswordEncoder().encodePassword(user.getPassword(), AppConstants.MD5_PASSWORD_ENCODER_SALT));
        user.setRegisterDate(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);
        user.setExpireDate(calendar.getTime());
        Role role = roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<Role>();
        roles.add(role);
        user.setRoles(roles);
        user.setMarkForDelete(false);
        User result = userRepository.save(user);

        Template template = new Template();
        template.setUserId(result.getId());
        template.setPhoneNumber(result.getPhoneNumber());
        template.setContact(result.getName());
        template.setCompanyName("阳澄湖大闸蟹集团");
        templateRepository.save(template);

        InvitationCode invitationCode = inviteRepository.findByCode(user.getInvitationCode());
        invitationCode.setMarkForDelete(true);
        invitationCode.setInviteUser(result);
        invitationCode.setUseDate(new Date());
        inviteRepository.saveAndFlush(invitationCode);
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

    public void sendRegisterCodeToPhone(String phoneNumber) throws ClientException{
        String code = IndentityCodeHelper.generateRegisterCode();
        RegisterCode registerCode = registerCodeRepository.findByPhoneNumberAndMarkForDelete(phoneNumber, false);
        if(registerCode != null){
            registerCode.setMarkForDelete(true);
            registerCodeRepository.save(registerCode);
        }
        RegisterCode newRegisterCode = new RegisterCode();
        newRegisterCode.setMarkForDelete(false);
        newRegisterCode.setPhoneNumber(phoneNumber);
        newRegisterCode.setRegisterCode(code);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 5);
        newRegisterCode.setExpireDate(calendar.getTime());

        String json = "{\"code\":\""+code+"\"}";
        MsgManager.sendSms(phoneNumber, AppConstants.PHONE_TEMPLATE_CODE_RIGESTER_VERIFY, json);
        registerCodeRepository.save(newRegisterCode);
    }

    public Boolean checkRegisterCode(String phoneNumber, String registerCodeString){
        RegisterCode registerCode = registerCodeRepository.findByPhoneNumberAndMarkForDeleteAndRegisterCode(phoneNumber, false, registerCodeString);
        if(registerCode != null){
            if(registerCode.getExpireDate().compareTo(new Date()) > 0){
                return true;
            }
        }
        return false;
    }

    public boolean forgetPassword(String phoneNumber){
        User user = userRepository.findUserByPhoneNumber(phoneNumber);
        if(user == null) return false;
        String newPassword = IndentityCodeHelper.generateIdentityCode(4);
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
