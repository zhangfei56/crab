package cn.nicky.crab.repository;

import cn.nicky.crab.model.po.Message;
import cn.nicky.crab.model.po.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public interface MessageRepository extends JpaRepository <Message, Integer>{

    Page<Message> findByToUser_IdAndIsReadOrderBySendDateDesc(Integer userId, Boolean read, Pageable pageable);

    Page<Message> findByToUser_IdOrderBySendDateDesc(Integer userId, Pageable pageable);

    Integer countByToUser_IdAndIsRead(Integer userId, boolean read);

}
