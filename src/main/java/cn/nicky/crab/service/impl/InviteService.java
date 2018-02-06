package cn.nicky.crab.service.impl;

import cn.nicky.crab.model.po.InvitationCode;
import cn.nicky.crab.model.po.User;
import cn.nicky.crab.repository.InviteRepository;
import cn.nicky.crab.repository.UserRepository;
import cn.nicky.crab.util.IndentityCodeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class InviteService {

    @Autowired
    InviteRepository inviteRepository;

    @Autowired
    UserRepository userRepository;

    public Page<InvitationCode> getInvitationCode(Boolean markForDelete, Pageable pageable)
    {
        Sort sort = new Sort(Sort.Direction.DESC, "createDate");
        PageRequest pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<InvitationCode> invitationCodes= null;
        if(markForDelete == null) {
            invitationCodes = inviteRepository.findAll(pageRequest);
        }else {
            invitationCodes = inviteRepository.findByMarkForDelete(markForDelete, pageRequest);
        }

        return invitationCodes;
    }

    public void addInvitationCode(int userId){
        User user = userRepository.getOne(userId);
        String invitationCodeString = IndentityCodeHelper.generateIdentityCode(8);
        invitationCodeString.toUpperCase();
        InvitationCode invitationCode = new InvitationCode();
        invitationCode.setCode(invitationCodeString);
        invitationCode.setCreateBy(user);
        invitationCode.setCreateDate(new Date());
        invitationCode.setMarkForDelete(false);
        inviteRepository.save(invitationCode);
    }

    public void destroyInvitationCode(int inviteId){
        InvitationCode invitationCode = inviteRepository.findOne(inviteId);
        invitationCode.setMarkForDelete(true);
        inviteRepository.saveAndFlush(invitationCode);
    }

    public Boolean checkInvitationCode(String code){
        InvitationCode invitationCode = inviteRepository.findByCodeAndMarkForDelete(code, false);
        return invitationCode == null? false:true;
    }

}
