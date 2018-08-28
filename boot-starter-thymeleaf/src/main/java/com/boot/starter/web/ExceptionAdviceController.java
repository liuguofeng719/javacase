package com.boot.starter.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/7/12 下午5:42
 * @see jdk 1.7
 **/
@RestControllerAdvice
//@ControllerAdvice
public class ExceptionAdviceController {

    @InitBinder
    public void init(WebDataBinder binder) {

    }

    @ModelAttribute
    public void model(Model model) {

    }

//    @ExceptionHandler(Exception.class)
//    public Object handlerException(Exception e, HttpServletRequest request) {
//        ResultResponse response = new ResultResponse();
//        if (e instanceof BusinessException) {
//            final BusinessException bs = (BusinessException) e;
//            response.setMsg(bs.getMsg());
//            response.setCode(bs.getCode());
//        } else {
//            response.setMsg("未知异常，联系管理员");
//            response.setCode("500");
//        }
//
//        final String contentType = request.getHeader("Content-Type");
//        final String accept = request.getHeader("Accept");
//        final String xRequestedWith = request.getHeader("X-Requested-With");
//        if ((contentType != null && contentType.contains("application/json"))
//                || (accept != null && accept.contains("application/json"))
//                || "XMLHttpRequest".equalsIgnoreCase(xRequestedWith)) {
//            return response;
//        } else {
//            ModelAndView modelAndView = new ModelAndView();
//            modelAndView.addObject("msg", e.getMessage());
//            modelAndView.addObject("url", request.getRequestURL());
//            modelAndView.addObject("stackTrace", e.getStackTrace());
//            modelAndView.setViewName("error/error");
//            return modelAndView;
//        }
//    }
}
