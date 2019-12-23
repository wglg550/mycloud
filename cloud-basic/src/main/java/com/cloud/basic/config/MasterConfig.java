package com.cloud.basic.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Description: 主数据源 一般insert用
 * @Param:
 * @return:
 * @Author: wangliang
 * @Date: 2019/12/19
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryMaster",
        transactionManagerRef = "transactionManagerMaster",
        basePackages = {"com.cloud.basic.master.dao"}) //设置Repository所在位置
public class MasterConfig {

    @Resource
    private Properties jpaProperties;

    @Value("${spring.datasource.master.url}")
    private String url;

    @Value("${spring.datasource.master.username}")
    private String username;

    @Value("${spring.datasource.master.password}")
    private String password;

    @Value("${spring.datasource.master.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.master.connect-properties}")
    private String connectProperties;

    @Value("${spring.datasource.master.filters}")
    private String filters;

    @Bean(name = "masterDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource druid() {
        //如果需要加密passwrod，则必须手动配置以下信息，此乃druid兼容性问题
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setConnectionProperties(connectProperties);
        try {
            dataSource.setFilters(filters);
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return dataSource;
    }

    @Bean(name = "entityManagerMaster")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryMaster(builder).getObject().createEntityManager();
    }

    @Bean(name = "entityManagerFactoryMaster")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryMaster(EntityManagerFactoryBuilder builder) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory
                = builder
                .dataSource(druid())
                .packages("com.cloud.basic.master.entity")//设置实体类所在位置
                .persistenceUnit("masterPersistenceUnit")//持久化单元创建一个默认即可，多个便要分别命名
                .build();
        entityManagerFactory.setJpaProperties(jpaProperties);
        return entityManagerFactory;
    }

    @Bean(name = "transactionManagerMaster")
    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryMaster(builder).getObject());
    }
}
