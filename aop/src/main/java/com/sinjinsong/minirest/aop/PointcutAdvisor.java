package com.sinjinsong.minirest.aop;

/**
 * 切点通知器，用于提供 对哪个对象的哪个方法进行什么样的拦截 的具体内容。
 * 通过它可以获取一个切点对象 Pointcut 和一个通知器对象 Advisor。
 */
public interface PointcutAdvisor extends Advisor {
    Pointcut getPointcut();
}
