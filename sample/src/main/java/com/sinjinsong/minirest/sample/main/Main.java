package com.sinjinsong.minirest.sample.main;

import com.sinjinsong.minirest.context.ApplicationContext;
import com.sinjinsong.minirest.context.support.ClassPathXmlApplicationContext;

/**
 * @author sinjinsong
 * @date 2018/3/4
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("minirest/minirest-applicationContext.xml");
        Person p = (Person) ctx.getBean("person");
        System.out.println(p);
    }
}
