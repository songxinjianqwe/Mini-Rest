package com.sinjinsong.minirest.beans.factory.config;

/**
 * @author sinjinsong
 * @date 2018/3/3
 * 初始化bean前后的钩子函数，可以用来实现AOP
 */
public interface BeanPostProcessor {
    Object postProcessBeforeInitialization(Object bean,String beanName) throws Exception;
    Object postProcessAfterInitialization(Object bean,String beanName) throws Exception;
}
