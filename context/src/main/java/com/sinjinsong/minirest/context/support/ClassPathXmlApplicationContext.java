package com.sinjinsong.minirest.context.support;

import com.sinjinsong.minirest.beans.exception.BeansException;
import com.sinjinsong.minirest.beans.factory.impl.DefaultListableBeanFactory;
import com.sinjinsong.minirest.beans.support.xml.XmlBeanDefinitionReader;
import com.sinjinsong.minirest.core.io.impl.UrlResourceLoader;

/**
 * @author sinjinsong
 * @date 2018/3/4
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {
    private String configLocation;

    public ClassPathXmlApplicationContext(String configLocation) throws BeansException {
        this(configLocation, new DefaultListableBeanFactory());
    }

    public ClassPathXmlApplicationContext(String configLocation, DefaultListableBeanFactory beanFactory) throws BeansException {
        super(beanFactory);
        this.configLocation = configLocation;
        // 初始化
        refresh();
    }

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, new UrlResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions(configLocation);
    }
}
