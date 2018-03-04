package com.sinjinsong.minirest.beans.factory.impl;

import com.sinjinsong.minirest.beans.enumeration.Autowire;
import com.sinjinsong.minirest.beans.exception.BeansException;
import com.sinjinsong.minirest.beans.factory.AutowireCapableBeanFactory;
import com.sinjinsong.minirest.beans.support.BeanPostProcessor;
import com.sinjinsong.minirest.beans.support.BeanReference;
import com.sinjinsong.minirest.beans.support.PropertyValue;
import com.sinjinsong.minirest.beans.support.beandefinition.BeanDefinition;
import com.sinjinsong.minirest.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * @author sinjinsong
 * @date 2018/3/3
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        // 1. createBeanInstance
        Object bean = createBeanInstance(beanDefinition);
        // 2. populateBean
        populateBean(bean, beanDefinition);
        // 3. initializeBean
        bean = initializeBean(bean, beanDefinition);
        return bean;
    }

    /**
     * 创建bean的实例
     *
     * @param beanDefinition
     * @return
     * @throws BeansException
     */
    protected Object createBeanInstance(BeanDefinition beanDefinition) throws BeansException {
        try {
            return beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 填充属性
     * @param bean
     * @param beanDefinition
     */
    protected void populateBean(Object bean, BeanDefinition beanDefinition) throws BeansException {
        // AutowireByName、AutowireByType
        if(beanDefinition.getAutowire() == Autowire.BYNAME) {
            autowireByName(bean,beanDefinition);
        }else if(beanDefinition.getAutowire() == Autowire.BYTYPE) {
            autowireByType(bean, beanDefinition);
        }
        applyPropertyValues(bean, beanDefinition);
    }
    
    /**
     * 按类型自动注入属性
     * @param bean
     * @param beanDefinition
     */
    protected void autowireByName(Object bean, BeanDefinition beanDefinition) {
        
    }

    /**
     * 按类型自动注入属性
     * @param bean
     * @param beanDefinition
     */
    protected void autowireByType(Object bean, BeanDefinition beanDefinition) {
        
    }

    /**
     * 设置属性
     *
     * @param bean
     * @param beanDefinition
     * @throws BeansException
     */
    @Override
    public void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws BeansException {
        for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()) {
            Object value = propertyValue.getValue();
            if (value instanceof BeanReference) {
				BeanReference beanReference = (BeanReference) value;
				value = getBean(beanReference.getBeanName());
			}
            Field field = ReflectionUtils.findField(beanDefinition.getBeanClass(), propertyValue.getName());
            ReflectionUtils.makeAccessible(field);
            try {
                field.set(bean, value);
            } catch (IllegalAccessException ex) {
                throw new BeansException("field " + propertyValue.getName() + " can not set");
            }
        }
    }

    /**
     * 调用BeanPostProcessor的钩子方法和调用用户自定义初始化方法
     *
     * @param bean
     * @param beanDefinition
     * @return
     */
    protected Object initializeBean(Object bean, BeanDefinition beanDefinition) {
        Object wrappedBean = bean;
        wrappedBean = applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanDefinition.getBeanName());
        try {
            invokeInitMethods(wrappedBean, beanDefinition);
        } catch (Throwable ex) {
            throw new BeansException(
                    beanDefinition.getBeanName() + "Invocation of init method failed", ex);
        }
        wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanDefinition.getBeanName());
        return wrappedBean;
    }

    /**
     * beanPostProcessor#before
     *
     * @param bean
     * @param beanName
     * @return
     */
    private Object applyBeanPostProcessorsBeforeInitialization(Object bean, String beanName) {
        for (BeanPostProcessor beanPostProcessor : super.getBeanPostProcessors()) {
            bean = beanPostProcessor.postProcessBeforeInitialization(bean, beanName);
        }
        return bean;
    }

    /**
     * 调用用户初始化方法
     *
     * @param bean
     * @param beanDefinition
     */
    private void invokeInitMethods(Object bean, BeanDefinition beanDefinition) {

    }

    /**
     * beanPostProcessor#after
     *
     * @param bean
     * @param beanName
     * @return
     */
    private Object applyBeanPostProcessorsAfterInitialization(Object bean, String beanName) {
        for (BeanPostProcessor beanPostProcessor : super.getBeanPostProcessors()) {
            bean = beanPostProcessor.postProcessAfterInitialization(bean, beanName);
        }
        return bean;
    }

}
