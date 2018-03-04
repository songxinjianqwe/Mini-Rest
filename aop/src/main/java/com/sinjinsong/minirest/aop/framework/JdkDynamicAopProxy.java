package com.sinjinsong.minirest.aop.framework;

import com.sinjinsong.minirest.aop.TargetSource;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @author sinjinsong
 * @date 2018/3/4
 */
public class JdkDynamicAopProxy extends AbstractAopProxy implements InvocationHandler {
    public JdkDynamicAopProxy(AdvisedSupport config) {
        super(config);
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), config.getTargetInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        TargetSource targetSource = this.config.getTargetSource();
        List<Object> chain = this.config.getInterceptors(method, targetSource.getTargetClass());
        MethodInterceptor methodInterceptor = config.getMethodInterceptor();
        if (config.getMethodMatcher() != null
                && config.getMethodMatcher().matches(method, config.getTargetSource().getTarget().getClass())) {
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(config.getTargetSource().getTarget(),
                    method, args));
        } else {
            return method.invoke(config.getTargetSource().getTarget(), args);
        }
        return null;
    }
}
