package com.sinjinsong.minirest.beans.factory.impl;

import com.sinjinsong.minirest.beans.factory.ConfigurableBeanFactory;
import com.sinjinsong.minirest.beans.support.beandefinition.BeanDefinition;
import com.sinjinsong.minirest.beans.support.BeanPostProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sinjinsong
 * @date 2018/3/3
 */
public abstract class AbstractBeanFactory implements ConfigurableBeanFactory {
    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();
    
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition)
            throws Exception;
    
    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.add(beanPostProcessor);
    }
    
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }
}
