package com.cloud.basic.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * @Description: 从数据源 一般select用
 * @Param:
 * @return:
 * @Author: wangliang
 * @Date: 2019/12/19
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactorySlave",
        transactionManagerRef = "transactionManagerSlave",
        basePackages = {"com.cloud.basic.slave.dao"}) //设置Repository所在位置
public class SlaveConfig {

    @Resource
    private Properties jpaProperties;

    @Bean(name = "slaveDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource druid() {
        return new DruidDataSource();
    }

    @Bean(name = "entityManagerSlave")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactorySlave(builder).getObject().createEntityManager();
    }

    @Bean(name = "entityManagerFactorySlave")
    public LocalContainerEntityManagerFactoryBean entityManagerFactorySlave(EntityManagerFactoryBuilder builder) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory
                = builder
                .dataSource(druid())
                .packages("com.cloud.basic.slave.entity")//设置实体类所在位置
                .persistenceUnit("slavePersistenceUnit")//持久化单元创建一个默认即可，多个便要分别命名
                .build();
        entityManagerFactory.setJpaProperties(jpaProperties);
        return entityManagerFactory;
    }

    @Bean(name = "transactionManagerSlave")
    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactorySlave(builder).getObject());
    }
}
