package com.sinjinsong.minirest.beans.factory;

import java.util.Map;

/**
 * @author sinjinsong
 * @date 2018/3/3
 */
public interface ListableBeanFactory extends BeanFactory {
    boolean containsBeanDefinition(String beanName);

    String[] getBeanDefinitionNames();

    String[] getBeanNamesForType(Class<?> type);

    <T> Map<String, T> getBeansOfType(Class<T> type);

}
