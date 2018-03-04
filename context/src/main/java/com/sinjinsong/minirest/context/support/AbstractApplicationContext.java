package com.sinjinsong.minirest.context.support;

import com.sinjinsong.minirest.beans.exception.BeansException;
import com.sinjinsong.minirest.beans.factory.ConfigurableListableBeanFactory;
import com.sinjinsong.minirest.beans.factory.impl.DefaultListableBeanFactory;
import com.sinjinsong.minirest.beans.support.BeanPostProcessor;
import com.sinjinsong.minirest.beans.utils.BeanFactoryUtils;
import com.sinjinsong.minirest.context.ConfigurableApplicationContext;

/**
 * @author sinjinsong
 * @date 2018/3/4
 */
public abstract class AbstractApplicationContext implements ConfigurableApplicationContext {
    private DefaultListableBeanFactory beanFactory;
    private final Object startupShutdownMonitor = new Object();


    public AbstractApplicationContext(DefaultListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     * 初始化
     *
     * @throws BeansException
     * @throws IllegalStateException
     */
    @Override
    public void refresh() throws BeansException {
        synchronized (this.startupShutdownMonitor) {
            // load bean definitions
            ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();
            // register bean post processors
            registerBeanPostProcessors(beanFactory);
            // getBean(singleton and non-lazy-init beans)
            finishBeanFactoryInitialization(beanFactory);
        }
    }

    protected ConfigurableListableBeanFactory obtainFreshBeanFactory() throws BeansException {
        refreshBeanFactory();
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        return beanFactory;
    }

    protected void refreshBeanFactory() throws BeansException {
        loadBeanDefinitions(beanFactory);
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory)
            throws BeansException;

    protected void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        for (String beanPostProcessor : BeanFactoryUtils.beanNamesForType(beanFactory, BeanPostProcessor.class)) {
            beanFactory.addBeanPostProcessor((BeanPostProcessor) getBean(beanPostProcessor));
        }
    }
    
    protected void finishBeanFactoryInitialization(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        beanFactory.preInstantiateSingletons();
    }

    /**
     * 销毁
     */
    @Override
    public void close() {
//        synchronized (this.startupShutdownMonitor) {
//            // Destroy all cached singletons in the context's BeanFactory.
//            destroyBeans();
//            // Close the state of this context itself.
//            closeBeanFactory();
//            // Let subclasses do some final clean-up if they wish...
//            onClose();
//        }
    }


    @Override
    public ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException {
        return this.beanFactory;
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return getBeanFactory().containsBeanDefinition(beanName);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public String[] getBeanNamesForType(Class<?> type) {
        return getBeanFactory().getBeanNamesForType(type);
    }

    @Override
    public Object getBean(String name) {
        return getBeanFactory().getBean(name);
    }
}
