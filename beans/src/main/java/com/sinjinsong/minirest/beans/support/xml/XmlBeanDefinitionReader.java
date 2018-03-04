package com.sinjinsong.minirest.beans.support.xml;

import com.sinjinsong.minirest.beans.exception.BeansException;
import com.sinjinsong.minirest.beans.support.beandefinition.AbstractBeanDefinitionReader;
import com.sinjinsong.minirest.beans.support.beandefinition.BeanDefinitionRegistry;
import com.sinjinsong.minirest.core.io.Resource;
import com.sinjinsong.minirest.core.io.ResourceLoader;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * @author sinjinsong
 * @date 2018/3/4
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public int loadBeanDefinitions(Resource resource) {
        try {
            InputStream inputStream = null;
            try {
                inputStream = resource.getInputStream();
                return doLoadBeanDefinitions(inputStream);
            } finally {
                inputStream.close();
            }
        } catch (Exception e) {
            throw new BeansException("Exception parsing XML document from " + resource, e);
        }
    }

    private int doLoadBeanDefinitions(InputStream inputStream) throws Exception {
        Document doc = doLoadDocument(inputStream);
        return registerBeanDefinitions(doc);
    }

    /**
     * 将InputStream转为Document
     *
     * @param inputStream
     * @return
     * @throws Exception
     */
    private Document doLoadDocument(InputStream inputStream) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        return docBuilder.parse(inputStream);
    }

    /**
     * 将Document注册到Registery中
     *
     * @param doc
     * @return
     */
    private int registerBeanDefinitions(Document doc) {
        BeanDefinitionDocumentReader documentReader = createBeanDefinitionDocumentReader();
        int countBefore = getRegistry().getBeanDefinitionCount();
        documentReader.registerBeanDefinitions(doc, getRegistry());
        return getRegistry().getBeanDefinitionCount() - countBefore;
    }
    
    
    private DefaultBeanDefinitionDocumentReader createBeanDefinitionDocumentReader() {
        return new DefaultBeanDefinitionDocumentReader();
    }

}
