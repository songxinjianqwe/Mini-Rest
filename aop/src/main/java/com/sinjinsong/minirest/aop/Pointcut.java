package com.sinjinsong.minirest.aop;


/**
 * Pointcut是join point的集合，是描述的一组符合某个条件的join point
 * 切点对象可以获取一个 ClassFilter 对象和一个 MethodMatcher 对象。
 * 前者用于判断是否对某个对象进行拦截（用于 筛选要代理的目标对象），
 * 后者用于判断是否对某个方法进行拦截（用于 在代理对象中对不同的方法进行不同的操作）。
 */
public interface Pointcut {
    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
}
