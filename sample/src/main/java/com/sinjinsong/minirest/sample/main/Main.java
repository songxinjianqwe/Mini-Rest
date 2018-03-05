package com.sinjinsong.minirest.sample.main;

import com.sinjinsong.minirest.context.ApplicationContext;
import com.sinjinsong.minirest.context.support.ClassPathXmlApplicationContext;
import com.sinjinsong.minirest.sample.service.LoginService;

/**
 * @author sinjinsong
 * @date 2018/3/4
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("minirest/minirest-applicationContext.xml");
        LoginService loginService = (LoginService) ctx.getBean("loginService");
        loginService.login("12345","12345");
    }
}
