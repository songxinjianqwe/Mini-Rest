package com.sinjinsong.minirest.beans.support.beandefinition;

import com.sinjinsong.minirest.beans.enumeration.Autowire;
import com.sinjinsong.minirest.beans.enumeration.Scope;
import com.sinjinsong.minirest.beans.support.PropertyValues;

/**
 * @author sinjinsong
 * @date 2018/3/3
 */
public class BeanDefinition {
    /**
     * bean的唯一标识
     */
    private String id;
    private Object bean;
    /**
     * beanName就是bean的全类名的字符串形式
     */
    private String beanName;
    private Class beanClass;
    private PropertyValues propertyValues = new PropertyValues();
    private Scope scope;
    private Autowire autowire;

    public BeanDefinition(String id,String beanName, String scopeStr, String autowireStr) {
        this.id = id;
        setBeanName(beanName);
        if(scopeStr != null) {
            this.scope  = Scope.valueOf(scopeStr.toUpperCase());
        }
        if(autowireStr != null) {
            this.autowire = Autowire.valueOf(autowireStr.toUpperCase());
        }
    }
    
    public Object getBean() {
        return bean;
    }

    public String getId() {
        return id;
    }

    public Class getBeanClass() {
        return beanClass;
    }


    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
        try {
            this.beanClass = Class.forName(beanName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }


    public Scope getScope() {
        return scope;
    }

    public Autowire getAutowire() {
        return autowire;
    }
}
