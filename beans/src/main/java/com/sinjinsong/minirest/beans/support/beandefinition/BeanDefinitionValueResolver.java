package com.sinjinsong.minirest.beans.support.beandefinition;

import com.sinjinsong.minirest.beans.factory.impl.AbstractAutowireCapableBeanFactory;
import com.sinjinsong.minirest.beans.support.BeanReference;
import com.sinjinsong.minirest.beans.support.PropertyValue;

/**
 * @author sinjinsong
 * @date 2018/3/4
 * 基于SPEL将字符串转为参数对应的类型
 */
public class BeanDefinitionValueResolver {
    private AbstractAutowireCapableBeanFactory beanFactory;
    public BeanDefinitionValueResolver(AbstractAutowireCapableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object resolveValueIfNecessary(PropertyValue pv) {
        Object value = pv.getValue();
        if (value instanceof BeanReference) {
            BeanReference beanReference = (BeanReference) value;
            value = beanFactory.getBean(beanReference.getBeanName());
        }
        
        return value;
    }
}
