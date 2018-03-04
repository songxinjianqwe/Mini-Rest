package com.sinjinsong.minirest.beans.factory;

import com.sinjinsong.minirest.beans.exception.BeansException;

/**
 * @author sinjinsong
 * @date 2018/3/4
 * 
 * 最底层的接口
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    void preInstantiateSingletons() throws BeansException;
}
