package com.sinjinsong.minirest.beans.factory.impl;

import com.sinjinsong.minirest.beans.exception.BeansException;
import com.sinjinsong.minirest.beans.factory.ConfigurableListableBeanFactory;
import com.sinjinsong.minirest.beans.support.beandefinition.BeanDefinition;
import com.sinjinsong.minirest.beans.support.beandefinition.BeanDefinitionRegistry;
import com.sinjinsong.minirest.util.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sinjinsong
 * @date 2018/3/4
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory
        implements ConfigurableListableBeanFactory, BeanDefinitionRegistry {
    
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private List<String> beanDefinitionNames = new ArrayList<>();

    @Override
    public Object getBean(String name) throws BeansException {
        BeanDefinition beanDefinition = getBeanDefinition(name);
        if (beanDefinition == null) {
            throw new IllegalArgumentException("bean " + name + " did not exist");
        }
        return createBean(name, beanDefinition);
    }

    @Override
    public void preInstantiateSingletons() throws BeansException {
        List<String> beanNames = new ArrayList<>(this.beanDefinitionNames);
        for (String beanName : beanNames) {
            getBean(beanName);
        }
    }


    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeansException {
        this.beanDefinitionMap.put(beanName, beanDefinition);
        this.beanDefinitionNames.add(beanName);
    }

    @Override
    public void removeBeanDefinition(String beanName) throws BeansException {
        this.beanDefinitionMap.remove(beanName);
        this.beanDefinitionNames.remove(beanName);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        return this.beanDefinitionMap.get(beanName);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return this.beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return StringUtils.toStringArray(this.beanDefinitionNames);
    }

    @Override
    public int getBeanDefinitionCount() {
        return this.beanDefinitionMap.size();
    }

    @Override
    public String[] getBeanNamesForType(Class<?> type) {
        List<String> result = new ArrayList<>();
        for (String beanName : this.beanDefinitionNames) {
            BeanDefinition beanDefinition = getBeanDefinition(beanName);
            if (type.isAssignableFrom(beanDefinition.getBeanClass())) {
                result.add(beanName);
            }
        }
        return StringUtils.toStringArray(result);
    }
    
    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type)
            throws BeansException {
        String[] beanNames = getBeanNamesForType(type);
        Map<String, T> result = new LinkedHashMap<>(beanNames.length);
        for (String beanName : beanNames) {
            Object beanInstance = getBean(beanName);
            result.put(beanName, (T) beanInstance);
        }
        return result;
    }
}
