package com.sinjinsong.minirest.aop.framework;

/**
 * JDK和CGLIB都继承该AopProxy
 */
public abstract class AbstractAopProxy implements AopProxy {
    
    protected AdvisedSupport advised;

    public AbstractAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }
}
