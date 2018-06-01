package com.demo.springboot;

import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/5/17 下午5:52
 * @see jdk 1.7
 **/
@Configuration
public class WebConfiguration {

    @Bean
    public RemoteIpFilter getRemotIpFilter() {
        return new RemoteIpFilter();
    }

    @Bean
    public FilterRegistrationBean getFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter());
        registration.setName("MyFilter");
        registration.setOrder(1);
        registration.addUrlPatterns("/*");
        registration.addInitParameter("init", "test");
        return registration;
    }

    class MyFilter implements Filter {

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {

        }

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            System.out.println(servletRequest.getServerPort());
        }

        @Override
        public void destroy() {

        }
    }
}
