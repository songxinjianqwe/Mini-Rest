package com.sinjinsong.minirest.beans.support;

import com.sinjinsong.minirest.beans.exception.BeansException;

/**
 * @author sinjinsong
 * @date 2018/3/3
 * 初始化bean前后的钩子函数，可以用来实现AOP
 */
public interface BeanPostProcessor {
    Object postProcessBeforeInitialization(Object bean,String beanName) throws BeansException;
    Object postProcessAfterInitialization(Object bean,String beanName) throws BeansException;
}
