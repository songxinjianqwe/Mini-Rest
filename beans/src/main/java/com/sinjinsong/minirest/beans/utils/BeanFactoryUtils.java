package com.sinjinsong.minirest.beans.utils;

import com.sinjinsong.minirest.beans.exception.BeansException;
import com.sinjinsong.minirest.beans.factory.ListableBeanFactory;
import com.sinjinsong.minirest.util.Assert;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author sinjinsong
 * @date 2018/3/3
 */
public class BeanFactoryUtils {
    public static String[] beanNamesForType(ListableBeanFactory beanFactory, Class<?> type) {
        return beanFactory.getBeanNamesForType(type);
    }
    
    public static <T> Map<String, T> beansOfType(ListableBeanFactory beanFactory, Class<T> type)
			throws BeansException {
		Assert.notNull(beanFactory, "ListableBeanFactory must not be null");
		Map<String, T> result = new LinkedHashMap<>(4);
		result.putAll(beanFactory.getBeansOfType(type));
		return result;
	}
}
