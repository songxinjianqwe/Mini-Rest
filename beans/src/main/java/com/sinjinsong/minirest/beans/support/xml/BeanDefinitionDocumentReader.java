package com.sinjinsong.minirest.beans.support.xml;

import com.sinjinsong.minirest.beans.exception.BeansException;
import com.sinjinsong.minirest.beans.support.beandefinition.BeanDefinitionRegistry;
import org.w3c.dom.Document;

/**
 * @author sinjinsong
 * @date 2018/3/4
 */
public interface BeanDefinitionDocumentReader {
    	void registerBeanDefinitions(Document doc, BeanDefinitionRegistry registry)
			throws BeansException;
}
