package cn.nicky.crab.controller;

import cn.nicky.crab.model.po.Message;
import cn.nicky.crab.security.SecurityUser;
import cn.nicky.crab.service.impl.MessageService;
import com.alibaba.fastjson.JSON;
import org.codehaus.groovy.runtime.dgmimpl.arrays.IntegerArrayGetAtMetaMethod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class MessageController {

    @Resource
    private MessageService messageService;

    @RequestMapping(value = "/client/messages", method = RequestMethod.GET)
    public String getMessages(Model model, Boolean read, Pageable pageable, Integer currentMessageIndex){
        read = read == null?false: read;
        currentMessageIndex = currentMessageIndex == null?0:currentMessageIndex;

        SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Page<Message> messagePage =messageService.getSelfMessage(securityUser.getId(), read, pageable);

        model.addAttribute("datas", messagePage);
        model.addAttribute("currentMessageIndex", currentMessageIndex);
        return "client/messageList";
    }

    @RequestMapping(value = "/client/message", method = RequestMethod.GET)
    public String addMessage(){


        return "client/addMessage";
    }

    @ResponseBody
    @RequestMapping(value = "/client/json/message", method = RequestMethod.POST)
    public String postMessage(Integer userId, String title, String text){
        SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        messageService.addMessage(securityUser.getId(), userId, title, text);

        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/client/json/messages/count", method = RequestMethod.GET)
    public int getUnreadMessagesCount(){
        SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return messageService.getSeftUnreadMessageCount(securityUser.getId());
    }

    @ResponseBody
    @RequestMapping(value = "/client/message/{messageId}", method = RequestMethod.GET)
    public String getMessageById(@PathVariable Integer messageId){
        Message message = messageService.getMessageById(messageId);

        return JSON.toJSONString(message);
    }


}
