package com.sinjinsong.minirest.beans.support.beandefinition;

import com.sinjinsong.minirest.beans.enumeration.Autowire;
import com.sinjinsong.minirest.beans.enumeration.Scope;
import com.sinjinsong.minirest.beans.support.PropertyValues;
import com.sinjinsong.minirest.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author sinjinsong
 * @date 2018/3/3
 */
@Slf4j
public class BeanDefinition {
    /**
     * bean的唯一标识
     */
    private String id;
    /**
     * beanName就是bean的全类名的字符串形式
     */
    private String beanName;
    private Class beanClass;
    private PropertyValues propertyValues = new PropertyValues();
    private Scope scope = Scope.SINGLETON;
    private Autowire autowire = Autowire.AUTOWIRE_NO;
    
    
    public BeanDefinition(String id, String beanName, String scopeStr, String autowireStr) {
        this.id = id;
        setBeanName(beanName);
        try {
            if (StringUtils.hasText(scopeStr)) {
                this.scope = Scope.valueOf(scopeStr.toUpperCase());
            }
            if (StringUtils.hasText(autowireStr)) {
                this.autowire = Autowire.valueOf(autowireStr.toUpperCase());
            }
        } catch (IllegalArgumentException e) {
            log.error("configuration file: scope or autowire error");
        }
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
