package com.sinjinsong.minirest.beans.factory;

import com.sinjinsong.minirest.beans.support.BeanDefinition;

/**
 * @author sinjinsong
 * @date 2018/3/3
 */
public interface AutowireCapableBeanFactory extends BeanFactory{
    void applyPropertyValues(Object bean, BeanDefinition mbd) throws Exception;
    
}
