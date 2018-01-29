package cn.nicky.crab.service.impl;

import cn.nicky.crab.model.po.*;
import cn.nicky.crab.repository.*;
import cn.nicky.crab.service.IMessageService;
import cn.nicky.crab.service.IOrderService;
import cn.nicky.crab.util.MsgManager;
import cn.nicky.crab.util.SyncCourierApiClient;
import com.alibaba.cloudapi.sdk.core.model.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by zhang on 2017/11/13.
 */
@Service
public class MessageService implements IMessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    private MsgManager msgManager;

    public Page<Message> getSelfMessage(Integer userId, Boolean read, Pageable pageable){

        Page<Message> messages = null;
        if(read){
            messages = messageRepository.findByToUser_IdAndIsReadOrderBySendDateDesc(userId, read, pageable);
        }else {
            messages = messageRepository.findByToUser_IdOrderBySendDateDesc(userId, pageable);
        }
        return messages;
    }

    public int getSeftUnreadMessageCount(Integer userId){
        return messageRepository.countByToUser_IdAndIsRead(userId, false);
    }

    public void addMessage(Integer sendUserId, Integer toUserId, String title, String text){
        User sendUser= userRepository.findOne(sendUserId);
        User toUser= userRepository.findOne(toUserId);

        Message message = new Message();
        message.setRead(false);
        message.setSendDate(new Date());
        message.setToUser(toUser);
        message.setText(text);
        message.setTitle(title);
        message.setSendUser(sendUser);
        messageRepository.save(message);

    }

    public Message getMessageById(Integer id){
        Message message = messageRepository.getOne(id);
        message.setRead(true);
        messageRepository.save(message);
        return message;
    }

}
