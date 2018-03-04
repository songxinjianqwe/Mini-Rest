package com.sinjinsong.minirest.beans.support;

import com.sinjinsong.minirest.beans.exception.BeansException;
import com.sinjinsong.minirest.beans.factory.BeanFactory;

/**
 * @author sinjinsong
 * @date 2018/3/4
 */
public interface BeanFactoryAware {
     void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
