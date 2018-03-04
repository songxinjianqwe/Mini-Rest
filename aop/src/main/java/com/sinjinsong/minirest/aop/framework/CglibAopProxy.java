package com.sinjinsong.minirest.aop.framework;

import net.sf.cglib.proxy.Enhancer;

/**
 */
public class CglibAopProxy extends AbstractAopProxy {

    public CglibAopProxy(AdvisedSupport config) {
        super(config);
    }

    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(config.getTargetSource().getTargetClass());
        enhancer.setInterfaces(config.getTargetSource().getInterfaces());
//		enhancer.setCallback(new DynamicAdvisedInterceptor(config));
        Object enhanced = enhancer.create();
        return enhanced;
    }

//	private static class DynamicAdvisedInterceptor implements MethodInterceptor {
//
//		private AdvisedSupport advised;
//
//		private org.aopalliance.intercept.MethodInterceptor delegateMethodInterceptor;
//
//		private DynamicAdvisedInterceptor(AdvisedSupport advised) {
//			this.advised = advised;
//			this.delegateMethodInterceptor = advised.getMethodInterceptor();
//		}
//
//		@Override
//		public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
//			if (advised.getMethodMatcher() == null
//					|| advised.getMethodMatcher().matches(method, advised.getTargetSource().getTargetClass())) {
//				return delegateMethodInterceptor.invoke(new CglibMethodInvocation(advised.getTargetSource().getTarget(), method, args, proxy));
//			}
//			return new CglibMethodInvocation(advised.getTargetSource().getTarget(), method, args, proxy).proceed();
//		}
//	}
//
//	private static class CglibMethodInvocation extends ReflectiveMethodInvocation {
//
//		private final MethodProxy methodProxy;
//
//		public CglibMethodInvocation(Object target, Method method, Object[] args, MethodProxy methodProxy) {
//			super(target, method, args);
//			this.methodProxy = methodProxy;
//		}
//
//		@Override
//		public Object proceed() throws Throwable {
//			return this.methodProxy.invoke(this.target, this.arguments);
//		}
//	}

}
