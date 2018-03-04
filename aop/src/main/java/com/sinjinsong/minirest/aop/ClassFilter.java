package com.sinjinsong.minirest.aop;

/**
 * 类过滤器
 */
public interface ClassFilter {
    boolean matches(Class<?> targetClass);
}
