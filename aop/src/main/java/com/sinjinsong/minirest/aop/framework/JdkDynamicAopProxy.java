package com.sinjinsong.minirest.aop.framework;

import com.sinjinsong.minirest.aop.TargetSource;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

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
        Object target = targetSource.getTarget();
        Class<?> targetClass = targetSource.getTargetClass();
        List<MethodInterceptor> interceptors = config.getInterceptors(method, targetClass);
        Object retVal;
        if (interceptors.size() == 0) {
            retVal = method.invoke(config.getTargetSource().getTarget(), args);
        }else{
            MethodInvocation invocation = new ReflectiveMethodInvocation(proxy,target,method,args,targetClass,interceptors);
            retVal = invocation.proceed();
        }
        return retVal;    
    }
}
