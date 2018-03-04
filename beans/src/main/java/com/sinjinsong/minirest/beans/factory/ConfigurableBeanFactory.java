package com.sinjinsong.minirest.beans.factory;

import com.sinjinsong.minirest.beans.support.BeanPostProcessor;

import java.util.List;

/**
 * @author sinjinsong
 * @date 2018/3/4
 */
public interface ConfigurableBeanFactory extends BeanFactory{
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
    List<BeanPostProcessor> getBeanPostProcessors();
}
