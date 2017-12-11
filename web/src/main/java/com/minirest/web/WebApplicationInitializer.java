package com.minirest.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @author songx
 * @date 2017/12/11
 */
public interface WebApplicationInitializer {
    void onStartup(ServletContext servletContext) throws ServletException;
}
