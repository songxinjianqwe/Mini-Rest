package com.sinjinsong.minirest.aop.aspectj.autoproxy;

import com.sinjinsong.minirest.aop.Advisor;
import com.sinjinsong.minirest.aop.TargetSource;
import com.sinjinsong.minirest.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.sinjinsong.minirest.aop.framework.ProxyFactory;
import com.sinjinsong.minirest.beans.exception.BeansException;
import com.sinjinsong.minirest.beans.factory.BeanFactory;
import com.sinjinsong.minirest.beans.factory.ListableBeanFactory;
import com.sinjinsong.minirest.beans.support.BeanFactoryAware;
import com.sinjinsong.minirest.beans.support.BeanPostProcessor;
import com.sinjinsong.minirest.beans.utils.BeanFactoryUtils;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AspectJAwareAdvisorAutoProxyCreator implements BeanPostProcessor, BeanFactoryAware {
    private ListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        if (beanFactory instanceof ListableBeanFactory) {
            this.beanFactory = (ListableBeanFactory) beanFactory;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (isInfrastructureClass(bean.getClass())) {
            return bean;
        }
        // 获取适合应用到该bean的所有advisor
        List<Advisor> specificInterceptors = getAdvicesAndAdvisorsForBean(bean.getClass(), beanName);
        if (specificInterceptors.size() > 0) {
            // 创建代理
            Object proxy = createProxy(new TargetSource(bean, bean.getClass(), bean.getClass().getInterfaces()), specificInterceptors);
            return proxy;
        }
        return bean;
    }

    private boolean isInfrastructureClass(Class<?> beanClass) {
        return beanClass.isAssignableFrom(AspectJExpressionPointcutAdvisor.class) ||
                beanClass.isAssignableFrom(MethodInterceptor.class);
    }

    /**
     * 获取适合应用在该bean上的所有Advisor
     *
     * @param beanClass
     * @param beanName
     * @return
     */
    private List<Advisor> getAdvicesAndAdvisorsForBean(Class<?> beanClass, String beanName) {
        List<Advisor> result = new ArrayList<>();
        Map<String, AspectJExpressionPointcutAdvisor> beans = BeanFactoryUtils.beansOfType(beanFactory, AspectJExpressionPointcutAdvisor.class);
        beans.forEach((advisorBeanName, advisor) -> {
            if (advisor.getPointcut().getClassFilter().matches(beanClass)) {
                result.add(advisor);
            }
        });
        return result;
    }

    /**
     * 创建代理
     *
     * @param advisors
     * @param targetSource
     * @return
     */
    protected Object createProxy(
            TargetSource targetSource, List<Advisor> advisors) {
        ProxyFactory proxyFactory = new ProxyFactory(targetSource);
        proxyFactory.addAdvisors(advisors);
        return proxyFactory.getProxy();
    }
}
