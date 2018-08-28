package com.boot.starter.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.alibaba.fastjson.support.spring.JSONPResponseBodyAdvice;
import com.boot.starter.domain.ResultResponse;
import com.boot.starter.interceptor.MyHandlerInterceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc 1，跨域，客户端处理，服务端处理 jackson 支持jsop 的配置，spring boot 默认的json 转换 jackson 2,fastjson 实现 jsonp
 *
 * 2种方式只能使用一种
 * @createtime 2018/6/27 下午5:48
 * @see jdk 1.7
 **/
@Configuration
public class FastJsonWebMvcConfiguration implements WebMvcConfigurer {


    private final Logger logger = LoggerFactory.getLogger(getClass());

    //fastjson 解决也需要设置跨域addCorsMappings，fastjson 还需要实现 configureMessageConverters
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowCredentials(true).allowedMethods(HttpMethod.GET.toString());
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        //2种方式选择其中一种
        //服务器端jsonp方式
        /**
         前端请求示例（注意dataType是json，不是jsonp）
         $.ajax({
         url: 'http://jsonp.itopener1.com:8081/jsonp/user/2',
         type: 'get',
         dataType: 'json',
         success: function(data){
         console.log(JSON.stringify(data));
         },
         error: function(err){
         console.log(JSON.stringify(err));
         }
         });
         **/
//        因为FastJsonHttpMessageConverter4 moren mediatype 为 (*/*),
//         restTemplate请求不允许请求头信息中的ContentType为*，所以需要修改mediaType
//        FastJsonHttpMessageConverter4 fastJsonHttpMessageConverter4 = new FastJsonHttpMessageConverter4();
//
//        List<MediaType> supportedMediaTypes = new ArrayList<>();
//        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
//        // fastJsonHttpMessageConverter4.getSupportedMediaTypes()方法获取的list不允许修改，所以只能使用set方法进行修改
//        fastJsonHttpMessageConverter4.setSupportedMediaTypes(supportedMediaTypes);
//        converters.add(fastJsonHttpMessageConverter4);

        //前端的jsonp 实现
        //jsonp 的方式，
//        converters.add(new FastJsonpHttpMessageConverter4()); 方法已经过时
        converters.add(new FastJsonHttpMessageConverter());
    }

    // fastjson 前端的jsonp 实现需要传递callback 参数，默认参数 "callback"
    @Bean
    public JSONPResponseBodyAdvice responseBodyAdvice() {
        return new JSONPResponseBodyAdvice();
    }

    //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyHandlerInterceptor());
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/");
//        registry.addResourceHandler("/static").addResourceLocations("classpath:/static/");
//    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add((request, response, handler, ex) -> {

            ResultResponse result = new ResultResponse();
            if (ex instanceof BusinessException) {
                result.setCode("200").setMsg(ex.getMessage());
            } else if (ex instanceof NoHandlerFoundException) {
                result.setCode("404").setMsg("接口【" + ex.getMessage() + "】不存在！");
            } else if (ex instanceof ServletException) {
                result.setCode("500").setMsg(ex.getMessage());
            } else {
                result.setCode("500").setMsg("接口【" + ex.getMessage() + "】内部接口错误，请联系管理员");
                String message;
                if (handler instanceof HandlerMethod) {
                    HandlerMethod handlerMethod = (HandlerMethod) handler;
                    message = String.format("接口 请求地址%s 出现异常，方法：%s.%s,异常摘要：%s",
                            request.getRequestURL(),
                            handlerMethod.getBean().getClass().getName(),
                            handlerMethod.getMethod().getName(),
                            ex.getMessage()
                    );

                } else {
                    message = ex.getMessage();
                }
                logger.error(message, ex);
            }
            responseJson(response, result);
            return new ModelAndView();
        });
    }

    private void responseJson(HttpServletResponse response, ResultResponse result) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(200);
        try {
            response.getWriter().print(JSON.toJSONString(result));
        } catch (IOException e) {
            logger.error("输出json 失败，{}", e.getMessage());
        }
    }
}


