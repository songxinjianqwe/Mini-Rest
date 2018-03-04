package com.sinjinsong.minirest.beans.support.xml;

import com.sinjinsong.minirest.beans.exception.BeansException;
import com.sinjinsong.minirest.beans.support.beandefinition.BeanDefinition;
import com.sinjinsong.minirest.beans.support.beandefinition.BeanDefinitionRegistry;
import com.sinjinsong.minirest.beans.support.BeanReference;
import com.sinjinsong.minirest.beans.support.PropertyValue;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author sinjinsong
 * @date 2018/3/4
 */
public class DefaultBeanDefinitionDocumentReader implements BeanDefinitionDocumentReader {
    @Override
    public void registerBeanDefinitions(Document doc, BeanDefinitionRegistry registry) throws BeansException {
        Element root = doc.getDocumentElement();
        doRegisterBeanDefinitions(root, registry);
    }

    private void doRegisterBeanDefinitions(Element root, BeanDefinitionRegistry registry) {
        NodeList nl = root.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                processBeanDefinition(ele, registry);
            }
        }
    }

    /**
     * 解析一个bean，将bean标签转为BeanDefinition
     *
     * @param ele
     * @param registry
     */
    private void processBeanDefinition(Element ele, BeanDefinitionRegistry registry) {
        String id = ele.getAttribute("id");
        String className = ele.getAttribute("class");
        String autowire = ele.getAttribute("autowire");
        String scope = ele.getAttribute("scope");
        BeanDefinition beanDefinition = new BeanDefinition(id,className,autowire,scope);
        processProperty(ele, beanDefinition);
        beanDefinition.setBeanName(className);
        registry.registerBeanDefinition(id, beanDefinition);
    }

    /**
     * 将bean标签中的property子标签转为BeanDefinition中的属性键值对
     *
     * @param ele
     * @param beanDefinition
     */
    private void processProperty(Element ele, BeanDefinition beanDefinition) {
        NodeList propertyNode = ele.getElementsByTagName("property");
        for (int i = 0; i < propertyNode.getLength(); i++) {
            Node node = propertyNode.item(i);
            if (node instanceof Element) {
                Element propertyEle = (Element) node;
                String name = propertyEle.getAttribute("name");
                String value = propertyEle.getAttribute("value");
                if (value != null && value.length() > 0) {
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
                } else {
                    String ref = propertyEle.getAttribute("ref");
                    if (ref == null || ref.length() == 0) {
                        throw new IllegalArgumentException("Configuration problem: <property> element for property '"
                                + name + "' must specify a ref or value");
                    }
                    BeanReference beanReference = new BeanReference(ref);
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, beanReference));
                }
            }
        }
    }


}
