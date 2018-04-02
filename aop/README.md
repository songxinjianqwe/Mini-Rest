客户端注册AspectJAutoProxyCreator
客户端注册自定义Aspect，实现了MethodInterceptor接口
客户端注册多个AspectJExpressionPointcutAdvisor，将自定义Aspect注入其中

ApplicationContext初始化时会加载所有的BeanPostProcessor，包括AspectJAutoProxyCreator
在doCreateBean时：
- createBeanInstance
- populateBean
- initializeBean
    - invoke aware methods
    - bean post processor before 
    - invoke init methods
    - bean post processor after -> AspectJAutoProxyCreator会获取所有的AspectJExpressionPointcutAdvisor
        然后委托给ProxyFactory 对bean创建代理，注入advisors
        由JdkDynamicAopProxy（实现了InvocationHandler）拦截所有方法
        它又会调用ReflectiveMethodInvocation的proceed方法，逐个调用自定义aspect，最后调用被代理的方法
        
        