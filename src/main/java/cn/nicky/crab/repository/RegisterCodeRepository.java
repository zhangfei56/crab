package cn.nicky.crab.repository;

import cn.nicky.crab.model.po.InvitationCode;
import cn.nicky.crab.model.po.RegisterCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhang on 2017/11/14.
 */
@Repository
public interface RegisterCodeRepository extends JpaRepository <RegisterCode, Integer>{
    RegisterCode findByPhoneNumberAndMarkForDelete(String phoneNumber, Boolean markForDelete);

    RegisterCode findByPhoneNumberAndMarkForDeleteAndRegisterCode(String phoneNumber, Boolean markForDelete, String code);

}
