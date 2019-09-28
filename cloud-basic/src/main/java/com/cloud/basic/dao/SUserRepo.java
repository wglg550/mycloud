package com.cloud.basic.dao;

import com.cloud.basic.entity.SUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SUserRepo extends JpaRepository<SUserEntity, Long>, JpaSpecificationExecutor<SUserEntity> {

}
