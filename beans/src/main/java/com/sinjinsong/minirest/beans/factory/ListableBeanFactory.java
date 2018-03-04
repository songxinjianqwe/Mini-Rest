package com.sinjinsong.minirest.beans.factory;

/**
 * @author sinjinsong
 * @date 2018/3/3
 */
public interface ListableBeanFactory extends BeanFactory {
    boolean containsBeanDefinition(String beanName);
    
    String[] getBeanDefinitionNames();
    
    String[] getBeanNamesForType(Class<?> type);

}
