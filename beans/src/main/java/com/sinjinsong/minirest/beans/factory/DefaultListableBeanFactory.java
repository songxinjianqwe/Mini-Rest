package com.sinjinsong.minirest.beans.factory;

import com.sinjinsong.minirest.beans.support.BeanDefinition;

/**
 * @author sinjinsong
 * @date 2018/3/3
 */
public class DefaultListableBeanFactory implements AutowireCapableBeanFactory,ListableBeanFactory{
    @Override
    public void applyPropertyValues(Object bean, BeanDefinition mbd) throws Exception {
        
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return false;
    }

    @Override
    public int getBeanDefinitionCount() {
        return 0;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return new String[0];
    }

    @Override
    public String[] getBeanNamesForType(Class<?> type) {
        return new String[0];
    }

    @Override
    public Object getBean(String name) throws Exception {
        return null;
    }
}
