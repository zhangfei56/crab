package cn.nicky.crab.dao;

import cn.nicky.crab.entity.SRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SRoleRepository extends JpaRepository<SRole,Integer> {



}