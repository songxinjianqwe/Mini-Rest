package com.sinjinsong.minirest.aop.framework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author sinjinsong
 * @date 2018/3/4
 */
public class JdkDynamicAopProxy extends AbstractAopProxy implements InvocationHandler {
    public JdkDynamicAopProxy(AdvisedSupport advised) {
        super(advised);
    }

    @Override
    public Object getProxy() {
        return null;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
