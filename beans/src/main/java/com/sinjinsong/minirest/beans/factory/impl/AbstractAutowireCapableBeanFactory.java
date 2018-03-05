package com.sinjinsong.minirest.beans.factory.impl;

import com.sinjinsong.minirest.beans.enumeration.Autowire;
import com.sinjinsong.minirest.beans.enumeration.Scope;
import com.sinjinsong.minirest.beans.exception.BeansException;
import com.sinjinsong.minirest.beans.factory.AutowireCapableBeanFactory;
import com.sinjinsong.minirest.beans.support.BeanFactoryAware;
import com.sinjinsong.minirest.beans.support.BeanPostProcessor;
import com.sinjinsong.minirest.beans.support.BeanReference;
import com.sinjinsong.minirest.beans.support.PropertyValue;
import com.sinjinsong.minirest.beans.support.beandefinition.BeanDefinition;
import com.sinjinsong.minirest.util.ReflectionUtils;
import com.sinjinsong.minirest.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author sinjinsong
 * @date 2018/3/3
 */
@Slf4j
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean = null;
        if (beanDefinition.getScope() == Scope.SINGLETON) {
            if (super.containsSingleton(beanName)) {
                return super.getSingleton(beanName);
            } else {
                bean = doCreateBean(beanDefinition);
                super.registerSingleton(beanName, bean);
            }
        } else if (beanDefinition.getScope() == Scope.PROTOTYPE) {
            bean = doCreateBean(beanDefinition);
        }
        return bean;
    }

    protected Object doCreateBean(BeanDefinition beanDefinition) {
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
     *
     * @param bean
     * @param beanDefinition
     */
    protected void populateBean(Object bean, BeanDefinition beanDefinition) throws BeansException {
        // AutowireByName、AutowireByType
        if (beanDefinition.getAutowire() == Autowire.BYNAME) {
            autowireByName(bean, beanDefinition);
        } else if (beanDefinition.getAutowire() == Autowire.BYTYPE) {
            autowireByType(bean, beanDefinition);
        }
        applyPropertyValues(bean, beanDefinition);
    }

    /**
     * 按类型自动注入属性
     *
     * @param bean
     * @param beanDefinition
     */
    protected void autowireByName(Object bean, BeanDefinition beanDefinition) {
        //TODO 
    }

    /**
     * 按类型自动注入属性
     *
     * @param bean
     * @param beanDefinition
     */
    protected void autowireByType(Object bean, BeanDefinition beanDefinition) {
        //TODO 
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
            String name = propertyValue.getName();
            Object value = propertyValue.getValue();
            if (value instanceof BeanReference) {
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getBeanName());
            }
            try {
                Method method = findSetter(name, beanDefinition.getBeanClass(), value.getClass());
                if (method == null) {
                    throw new BeansException("field " + propertyValue.getName() + " did not found");
                }
                ReflectionUtils.makeAccessible(method);
                method.invoke(bean, value);
            } catch (Exception e) {
                e.printStackTrace();
                throw new BeansException("field " + propertyValue.getName() + " did not found or can not be set");
            }
        }
    }

    /**
     * 找到该类的该属性名对应的setter方法
     * @param fieldName
     * @param beanClass
     * @param fieldType
     * @return
     */
    private Method findSetter(String fieldName, Class<?> beanClass, Class<?> fieldType) {
        Field field = null;
        try {
            field = beanClass.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            try {
                return beanClass.getDeclaredMethod(fieldToSetterName(fieldName), fieldType);
            } catch (NoSuchMethodException ex) {
                return null;
            }
        }
        try {
            return beanClass.getDeclaredMethod(fieldToSetterName(fieldName), new Class[]{field.getType()});
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    private String fieldToSetterName(String field) {
        return new StringBuilder().append("set").append(StringUtils.capitalize(field)).toString();
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
        invokeAwareMethods(beanDefinition.getBeanName(), bean);
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
     * 根据bean实现的各种Aware接口，注入相应的对象
     *
     * @param beanName
     * @param bean
     */
    private void invokeAwareMethods(String beanName, Object bean) {
        if (bean instanceof BeanFactoryAware) {
            ((BeanFactoryAware) bean).setBeanFactory(AbstractAutowireCapableBeanFactory.this);
        }
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
