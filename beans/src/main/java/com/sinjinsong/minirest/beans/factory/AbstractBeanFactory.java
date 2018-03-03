package com.sinjinsong.minirest.beans.factory;

import com.sinjinsong.minirest.beans.factory.config.BeanPostProcessor;
import com.sinjinsong.minirest.beans.support.BeanDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sinjinsong
 * @date 2018/3/3
 */
public abstract class AbstractBeanFactory implements BeanFactory {
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private List<String> beanDefinitionNames = new ArrayList<>();

    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    @Override
    public Object getBean(String name) throws Exception {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (beanDefinition == null) {
            throw new IllegalArgumentException("bean " + name + " did not exist");
        }
        Object bean = beanDefinition.getBean();
        if (bean == null) {
            bean = doCreateBean(beanDefinition);
            bean = initializeBean(bean, name);
            beanDefinition.setBean(bean);
        }
        return bean;
    }

    /**
     * 初始化bean，可以进行AOP
     *
     * @param bean
     * @param name
     * @return
     * @throws Exception
     */
    private Object initializeBean(Object bean, String name) throws Exception {
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessBeforeInitialization(bean, name);
        }

        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessAfterInitialization(bean, name);
        }
        return bean;
    }

    /**
     * 创建bean
     *
     * @param beanDefinition
     * @return
     */
    protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
        Object bean = createBeanInstance(beanDefinition);
        beanDefinition.setBean(bean);
        // 装配属性
        applyPropertyValues(bean, beanDefinition);
        return bean;
    }

    /**
     * 留给子类来实现，用于装配属性
     * @param bean
     * @param beanDefinition
     */
    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) {
        // 留给子类来实现
    }

    private Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
        return beanDefinition.getBeanClass().newInstance();
    }

    /**
     * 在初始化BeanFactory时一次性初始化
     * @throws Exception
     */
    public void preInstantiateSingletons() throws Exception {
        List<String> beanNames = new ArrayList<>(this.beanDefinitionNames);
        for (String beanName : beanNames) {
            getBean(beanName);
        }
    }
    
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name,beanDefinition);
        beanDefinitionNames.add(name);
    }
    
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.add(beanPostProcessor);
    }
}
