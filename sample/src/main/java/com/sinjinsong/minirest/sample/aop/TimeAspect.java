package com.sinjinsong.minirest.sample.aop;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author sinjinsong
 * @date 2018/3/5
 */
@Slf4j
public class TimeAspect implements MethodInterceptor{
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        log.info("Time: The method [ {} ] begins at: {}", methodInvocation.getMethod(), System.currentTimeMillis());
        Object retVal;
        try{
            retVal = methodInvocation.proceed();
        } catch (Throwable e) {
            log.info("Time: The method [ {} ] throws an exception at: {}", methodInvocation.getMethod(),  System.currentTimeMillis());
            throw e;
        }
        log.info("Time: The method [ {} ] ends at: {}", methodInvocation.getMethod(),  System.currentTimeMillis());
        return retVal;
    }
}
