package com.boot.starter.utils;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * 1，跨域，客户端处理，服务端处理
 *  jackson 支持jsop 的配置，spring boot 默认的json 转换 jackson
 * 2,fastjson 实现 jsonp
 *
 * 2种方式只能使用一种
 * @createtime 2018/6/27 下午5:48
 * @see jdk 1.7
 **/
//@Configuration
//public class WebMvcConfiguration implements WebMvcConfigurer {
//
//    //1,jackson 服务器端解决
//    //2,fastjson 解决也需要设置跨域addCorsMappings，fastjson 还需要实现 configureMessageConverters
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**").allowCredentials(true).allowedMethods(HttpMethod.GET.toString());
//    }
//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

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
        //因为FastJsonHttpMessageConverter4 moren mediatype 为 (*/*),
        // restTemplate请求不允许请求头信息中的ContentType为*，所以需要修改mediaType
//        FastJsonHttpMessageConverter4 fastJsonHttpMessageConverter4 = new FastJsonHttpMessageConverter4();
//
//        List<MediaType> supportedMediaTypes = new ArrayList<>();
//        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
//        // fastJsonHttpMessageConverter4.getSupportedMediaTypes()方法获取的list不允许修改，所以只能使用set方法进行修改
//        fastJsonHttpMessageConverter4.setSupportedMediaTypes(supportedMediaTypes);
//        converters.add(fastJsonHttpMessageConverter4);

        //前端的jsonp 实现
        //jsonp 的方式，
//        converters.add(new FastJsonpHttpMessageConverter4());
//    }

    // fastjson 前端的jsonp 实现，默认参数 "callback", "jsonp"
//    @Bean
//    public FastJsonpResponseBodyAdvice responseBodyAdvice(){
//        return new FastJsonpResponseBodyAdvice();
//    }
//}
