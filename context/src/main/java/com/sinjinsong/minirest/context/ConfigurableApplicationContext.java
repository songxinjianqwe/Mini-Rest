package com.sinjinsong.minirest.context;

import com.sinjinsong.minirest.beans.exception.BeansException;
import com.sinjinsong.minirest.beans.factory.ConfigurableListableBeanFactory;

/**
 * @author sinjinsong
 * @date 2018/3/4
 */
public interface ConfigurableApplicationContext extends ApplicationContext{
    void refresh() throws BeansException, IllegalStateException;
    void close();
    ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException;
}
