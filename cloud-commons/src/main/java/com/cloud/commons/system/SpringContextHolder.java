package com.cloud.commons.system;

import com.cloud.commons.Exception.BusinessException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description: spring上下文获取工具
 * @Param:
 * @return:
 * @Author: wangliang
 * @Date: 2019/10/11
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {
    /**
     * 上下文对象实例
     */
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }

    /**
     * 获取application上下文对象
     *
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        assertApplicationContext();
        return applicationContext;
    }

    /**
     * 通过name获取bean
     *
     * @param beanName
     * @return
     */
//    @SuppressWarnings("unchecked")//忽略警告
    public static <T> T getBean(String beanName) {
        assertApplicationContext();
        return (T) applicationContext.getBean(beanName);
    }

    /**
     * 通过class获取bean
     *
     * @param requiredType
     * @return
     */
    public static <T> T getBean(Class<T> requiredType) {
        assertApplicationContext();
        return (T) applicationContext.getBean(requiredType);
    }

    /**
     * 获取实例
     */
    private static void assertApplicationContext() {
        if (SpringContextHolder.applicationContext == null) {
            throw new BusinessException("SpringContextHolderException", "applicaitonContext属性为null,请检查是否注入了SpringContextHolder!");
        }
    }
}
