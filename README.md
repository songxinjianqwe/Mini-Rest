# Java实现简化版的Spring IOC+AOP+MVC

就Spring WebMvc而言，只实现其REST部分，不实现MVC部分。

已经实现了最简化的IOC&AOP

## IOC TODO
- close
- autowireByName/Type propertyValue可以为任意基础类型
- component-scan @Autowired @Component
- singleton 检测循环依赖
- 调用init-method,destroy-method

## AOP TODO
- 实现类似于使用SpringAOP的方式,封装Advisor
- 实现注解@Aspect @Pointcut @Before @After @AfterReturning
- CGLIB动态代理

