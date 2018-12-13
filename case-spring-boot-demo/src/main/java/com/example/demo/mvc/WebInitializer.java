package com.example.demo.mvc;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc 通过实现WebApplicationInitializer 接口相当于web.xml
 * @createtime 2018/6/15 下午3:57
 * @see jdk 1.7
 **/
public class WebInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        //新建WebapplicationContext 注册配置类MyMvcConfig， 并将其和当前servletContext关联
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(MyMvcConfig.class);
        context.setServletContext(servletContext);
        //注册Spring mvc DispatcherServlet
        final ServletRegistration.Dynamic servlet =
                servletContext.addServlet("dispather", new DispatcherServlet(context));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }
}
