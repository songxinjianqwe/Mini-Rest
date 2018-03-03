package com.sinjinsong.minirest.beans.factory;

/**
 * @author sinjinsong
 * @date 2018/3/3
 */
public interface ListableBeanFactory extends BeanFactory {
    boolean containsBeanDefinition(String beanName);

    int getBeanDefinitionCount();

    String[] getBeanDefinitionNames();


    String[] getBeanNamesForType(Class<?> type);


//    String[] getBeanNamesForType(Class<?> type, boolean includeNonSingletons, boolean allowEagerInit);
//	
//    <T> Map<String, T> getBeansOfType(Class<T> type) throws Exception;
//
//    <T> Map<String, T> getBeansOfType(Class<T> type, boolean includeNonSingletons, boolean allowEagerInit)
//            throws Exception;
//
//    String[] getBeanNamesForAnnotation(Class<? extends Annotation> annotationType);
}
