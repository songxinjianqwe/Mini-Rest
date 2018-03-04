package com.sinjinsong.minirest.aop.framework;

import com.sinjinsong.minirest.aop.Advisor;
import com.sinjinsong.minirest.aop.MethodMatcher;
import com.sinjinsong.minirest.aop.TargetSource;
import com.sinjinsong.minirest.aop.aspectj.AspectJExpressionPointcut;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 代理相关的元数据
 */
public class AdvisedSupport {

    private boolean proxyTargetClass = false;

    private List<Class<?>> interfaces = new ArrayList<>();

    private List<Advisor> advisors = new LinkedList<>();

    private TargetSource targetSource;


    public Class<?> getTargetClass() {
        return this.targetSource.getTargetClass();
    }

    public boolean isProxyTargetClass() {
        return proxyTargetClass;
    }

    public void setProxyTargetClass(boolean proxyTargetClass) {
        this.proxyTargetClass = proxyTargetClass;
    }

    public Class<?>[] getTargetInterfaces() {
        return targetSource.getInterfaces();
    }

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    /**
     * Set the interfaces to be proxied.
     */
    public void setInterfaces(Class<?>... interfaces) {
        Assert.notNull(interfaces, "Interfaces must not be null");
        this.interfaces.clear();
        for (Class<?> ifc : interfaces) {
            addInterface(ifc);
        }
    }

    /**
     * Add a new proxied interface.
     *
     * @param intf the additional interface to proxy
     */
    public void addInterface(Class<?> intf) {
        if (!intf.isInterface()) {
            throw new IllegalArgumentException("[" + intf.getName() + "] is not an interface");
        }
        if (!this.interfaces.contains(intf)) {
            this.interfaces.add(intf);
        }
    }

    public void addAdvisor(Advisor advisor) {
        this.advisors.add(advisor);
    }

    public void addAdvisors(List<Advisor> advisors) {
        for (Advisor advisor : advisors) {
            this.advisors.add(advisor);
        }
    }


    /**
     * 将Advisor封装为InterceptorAndDynamicMethodMatcher
     *
     * @param method
     * @param targetClass
     * @return
     */
    public List<MethodInterceptor> getInterceptors(Method method, Class<?> targetClass) {
        List<MethodInterceptor> result = new ArrayList<>();
        for (Advisor advisor : advisors) {
            if (advisor instanceof AspectJExpressionPointcut) {
                AspectJExpressionPointcut pointcutAdvisor = (AspectJExpressionPointcut) advisor;
                if (pointcutAdvisor.getClassFilter().matches(targetClass)) {
                    MethodMatcher mm = pointcutAdvisor.getMethodMatcher();
                    if (mm.matches(method, targetClass)  ) {
                        result.add((MethodInterceptor) advisor.getAdvice());
                    }
                }
            }
        }
        return result;
    }
}
