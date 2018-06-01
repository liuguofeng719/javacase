package com.example.demo.core.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc 编写切面
 * @createtime 2018/5/31 下午4:38
 * @see jdk 1.7
 **/
@Aspect //注解申明一个切面
@Component
public class LogAopAspect {

//    execution(modifiers-pattern? ret-type-pattern declaring-type-pattern? name-pattern(param-pattern)throws-pattern?)
//    returning type pattern,name pattern, and parameters pattern是必须的.
//    ret-type-pattern:可以为*表示任何返回值,全路径的类名等.
//    name-pattern:指定方法名, *代表所有
//    set*代表以set开头的所有方法.
//    parameters pattern:指定方法参数(声明的类型),(..)代表所有参数,(*)代表一个参数
//    (*,String)代表第一个参数为任何值,第二个为String类型.

    //注解申明切点，申明需要拦截的注解
    @Pointcut("@annotation(com.example.demo.core.aop.Action)")
    public void annotionPointCut() {
    }

    @Before("execution(* com.example.demo.core.aop.UserService.*(..))")
    public void before(JoinPoint joinPoint) {
        final MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        final Method method = signature.getMethod();
        System.out.println("被拦截的方法名字=" + method.getName());

    }

    @After("annotionPointCut()")
    public void after(JoinPoint joinPoint) {
        final MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        final Method method = signature.getMethod();
        final Action annotation = method.getAnnotation(Action.class);
        System.out.println(" 注解里面的值 = " + annotation.name());
    }
}
