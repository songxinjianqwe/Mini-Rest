package com.sinjinsong.minirest.beans.support;

import com.sinjinsong.minirest.beans.exception.BeansException;

/**
 * @author sinjinsong
 * @date 2018/3/4
 */
public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition)
            throws BeansException;
    
    void removeBeanDefinition(String beanName) throws BeansException;

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    boolean containsBeanDefinition(String beanName);

    String[] getBeanDefinitionNames();

}
