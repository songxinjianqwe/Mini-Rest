package com.sinjinsong.minirest.sample.aop;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.util.Arrays;

/**
 * @author sinjinsong
 * @date 2018/3/5
 */
@Slf4j
public class LoggingAspect implements MethodInterceptor {
    
    
    public void beforeMethod() {
        
    }
    
    
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        log.info("Log: The method [ {} ] begins with Parameters: {}", methodInvocation.getMethod(), Arrays.toString(methodInvocation.getArguments()));
        Object retVal;
        try {
            retVal = methodInvocation.proceed();
        } catch (Throwable e) {
            log.error("Log: Error happened in method: [ {} ]", methodInvocation.getMethod());
            log.error("Log: Parameters: {}", Arrays.toString(methodInvocation.getArguments()));
            log.error("Log: Exception StackTrace: {}", e);
            throw e;
        }
        log.info("Log: The method [ {} ] ends with Result: {}", methodInvocation.getMethod(), retVal);
        return retVal;
    }
}
