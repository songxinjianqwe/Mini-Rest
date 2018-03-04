package com.sinjinsong.minirest.beans.factory.impl;

import com.sinjinsong.minirest.beans.exception.BeansException;
import com.sinjinsong.minirest.beans.support.BeanPostProcessor;
import com.sinjinsong.minirest.beans.factory.ConfigurableBeanFactory;
import com.sinjinsong.minirest.beans.support.BeanDefinition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sinjinsong
 * @date 2018/3/3
 */
public abstract class AbstractBeanFactory implements ConfigurableBeanFactory {
    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();
    
        
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition)
            throws BeansException;
    
    
    
    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.add(beanPostProcessor);
    }
    
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }
}
