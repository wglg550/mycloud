package com.cloud.basic.dao;

import com.cloud.basic.entity.SUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @Description: s_user repo
 * @Param:
 * @return:
 * @Author: wangliang
 * @Date: 2019/10/11
 */
@Repository
public interface SUserRepo extends JpaRepository<SUserEntity, Long>, JpaSpecificationExecutor<SUserEntity> {

    /**
     * 根据name查找
     *
     * @param name
     * @return
     */
    SUserEntity findByName(String name);
}
