package cn.nicky.crab.repository;

import cn.nicky.crab.model.po.CourierCompany;
import cn.nicky.crab.model.po.InvitationCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhang on 2017/11/14.
 */
@Repository
public interface InviteRepository extends JpaRepository <InvitationCode, Integer>{

    Page<InvitationCode> findByMarkForDelete(boolean markForDelete, Pageable pageable);

    InvitationCode findByCode(String code);

    InvitationCode findByCodeAndMarkForDelete(String code, boolean markForDelete);

    @Modifying
    @Transactional
    @Query(value = "update voucher v set v.status = ?1 where v.identity_code = ?2", nativeQuery = true)
    int useInvitationCode(int status, String code);
}
