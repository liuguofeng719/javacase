package com.exercise.reflect.crazybee;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

/**
 * @author guofeng
 * @version 1.0
 *
 * <p>
 *     java.lang.annotation.Annotation
 *     java.lang.reflect.AnnotatedElement
*  1、java内置注解
 * 这些注解叫的元注解,注解的注解
 * @Target 注解作用 方法，字段，类，参数 ，包等
 * @Documented 标记生成文档的时候生成注解
 * @Retention 注解在什么阶段起作用 RUNTIME > class > source
 * @Inherited  是否继承
 * @Repeatable 是否重复使用
 * </p>
 * 2、自定义注解
 * 1.1 声明注解
 * 修饰符 @interface MyName
 * public @interface MyAnnotation {
 * Stringvalue() default "";
 * }
 * 当只有一个值的之后，名字叫value的时候，使用不用写value, 例如：@MyAnnotation("java")
 * 3、通过反射操作注解
 * orm 数据库映射 POJO
 * @see jdk 1.8
 * 反射注解使用
 * <p>
 *     java 中在用的注解
 *      @Override 重写
 * 		@Deprecated 过时
 * 		@SuppressWarnings 消除警告
 * 		@SafeVarargs 消除安全
 * 		@FunctionalInterface 函数是接口
 * 	</p>
 **/
public class AnnotationUseReflect {

    public static void main(String[] args) throws Exception {
        final Class<?> aClass = Class.forName("com.exercise.reflect.crazybee.UserInfo");
        //当前类的注解，包括继承得到的
        final Annotation[] annotations = aClass.getAnnotations();

        for (final Annotation annotation : annotations) {
            System.out.println(annotation);
        }
        System.out.println("=====================");
        //当前类自己声明的注解
        final Annotation[] declaredAnnotations = aClass.getDeclaredAnnotations();
        for (final Annotation annotation : declaredAnnotations) {
            System.out.println(annotation);
            if (annotation instanceof CrazyBeeClassDesc) {
                System.out.println(((CrazyBeeClassDesc) annotation).value());
            } else if (annotation instanceof CrazyBeeTableName) {
                System.out.println(((CrazyBeeTableName) annotation).value());
            }
        }

        final Field[] fields = aClass.getDeclaredFields();
        for (final Field field : fields) {
            final Annotation[] declaredAnnotations1 = field.getDeclaredAnnotations();
            for (final Annotation annotation : declaredAnnotations1) {
                if (annotation instanceof CrazyBeeTableField) {
                    CrazyBeeTableField crazyBeeTableField = ((CrazyBeeTableField) (annotation));
                    final String value = crazyBeeTableField.value();
                    final String type = crazyBeeTableField.type();
                    System.out.println(value + " = " + type);
                }
            }
        }
    }
}

/**
 * 自定义表名注解
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface CrazyBeeTableName {
    String value();
}

/**
 * 自定义列名
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface CrazyBeeTableField {
    String value() default "";
    String type() default "";
}
/**
 * 自定义描述
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface CrazyBeeClassDesc {
    String value() default "";
}

@CrazyBeeClassDesc("用户信息")
@CrazyBeeTableName("user_info")
class UserInfo {

    @CrazyBeeTableField(value = "u_id", type = "bigint")
    private Long uid;
    @CrazyBeeTableField(value = "u_name", type = "varchar")
    private String uname;
    @CrazyBeeTableField(value = "address", type = "varchar")
    private String address;
    @CrazyBeeTableField(value = "age", type = "int")
    private int age;
    @CrazyBeeTableField(value = "idNo", type = "varchar")
    private String idNo;
    @CrazyBeeTableField(value = "u_name", type = "varchar")
    private String email;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}




