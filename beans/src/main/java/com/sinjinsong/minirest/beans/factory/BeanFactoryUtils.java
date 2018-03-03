package com.sinjinsong.minirest.beans.factory;

/**
 * @author sinjinsong
 * @date 2018/3/3
 */
public class BeanFactoryUtils {
    public static String[] beanNamesForType(ListableBeanFactory beanFactory, Class<?> type) {
        return beanFactory.getBeanNamesForType(type);
    }
}
