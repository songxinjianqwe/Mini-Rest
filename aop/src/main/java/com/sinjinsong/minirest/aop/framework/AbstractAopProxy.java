package com.sinjinsong.minirest.aop.framework;

/**
 * JDK和CGLIB都继承该AopProxy
 */
public abstract class AbstractAopProxy implements AopProxy {
    
    protected AdvisedSupport config;

    public AbstractAopProxy(AdvisedSupport config) {
        this.config = config;
    }
}
