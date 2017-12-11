package com.minirest.web.context;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author songx
 * @date 2017/12/11
 * 
 * Web项目启动时会调用该Listener的initialized方法，关闭时会调用destroyed方法
 */
public class ContextLoaderListener extends ContextLoader implements ServletContextListener {
    public ContextLoaderListener(){
        
    }
    public ContextLoaderListener(WebApplicationContext context){
        super(context);
    }
    
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
