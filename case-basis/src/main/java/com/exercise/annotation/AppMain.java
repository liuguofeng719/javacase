package com.exercise.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/2/16 8:21 PM
 * @see jdk 1.7
 **/
public class AppMain {
    public static void main(String[] args) {
//        test01();
        test02();
    }

    private static void test02() {
        final Class<AnnotationTest> testClass = AnnotationTest.class;
        final Annotation[] annotations = testClass.getAnnotations();
        for (final Annotation annotation : annotations) {
            System.out.println(annotation.toString());
        }
        System.out.println(AnnotationTest.class.isAnnotationPresent(PostMapping.class));
        System.out.println(AnnotationTest.class.isAnnotationPresent(RequestMapping.class));

        final Annotation[] declaredAnnotations = AnnotationTest.class.getDeclaredAnnotations();
        for (final Annotation declaredAnnotation : declaredAnnotations) {
            System.out.println(declaredAnnotation.toString());
        }

        System.out.println(findAnnotation(AnnotationTest.class, RequestMapping.class, new HashSet<>()));
    }

    /**
     * Find a single {@link Annotation} of {@code annotationType} on the
     * supplied {@link Class}, traversing its interfaces, annotations, and
     * superclasses if the annotation is not <em>directly present</em> on
     * the given class itself.
     * <p>This method explicitly handles class-level annotations which are not
     * declared as {@link java.lang.annotation.Inherited inherited} <em>as well
     * as meta-annotations and annotations on interfaces</em>.
     * <p>The algorithm operates as follows:
     * <ol>
     * <li>Search for the annotation on the given class and return it if found.
     * <li>Recursively search through all annotations that the given class declares.
     * <li>Recursively search through all interfaces that the given class declares.
     * <li>Recursively search through the superclass hierarchy of the given class.
     * </ol>
     * <p>Note: in this context, the term <em>recursively</em> means that the search
     * process continues by returning to step #1 with the current interface,
     * annotation, or superclass as the class to look for annotations on.
     *
     * @param clazz          the class to look for annotations on
     * @param annotationType the type of annotation to look for
     * @return the first matching annotation, or {@code null} if not found
     */
    private static <A extends Annotation> A findAnnotation(Class<?> clazz, Class<A> annotationType, Set<Annotation> visited) {
        try {
            Annotation[] anns = clazz.getDeclaredAnnotations();
            for (Annotation ann : anns) {
                if (ann.annotationType() == annotationType) {
                    return (A) ann;
                }
            }

            for (Annotation ann : anns) {
                // 检查类所有声明的注解是否是java.lang.Annotation
                if (!isInJavaLangAnnotationPackage(ann) && visited.add(ann)) {
                    // 如果不是递归查找注解类上面标识的注解
                    A annotation = findAnnotation(ann.annotationType(), annotationType, visited);
                    if (annotation != null) {
                        return annotation;
                    }
                }
            }
        } catch (Throwable ex) {
            return null;
        }

        for (Class<?> ifc : clazz.getInterfaces()) {
            A annotation = findAnnotation(ifc, annotationType, visited);
            if (annotation != null) {
                return annotation;
            }
        }

        Class<?> superclass = clazz.getSuperclass();
        if (superclass == null || Object.class == superclass) {
            return null;
        }
        return findAnnotation(superclass, annotationType, visited);
    }

    public static boolean isInJavaLangAnnotationPackage(Annotation annotation) {
        return (annotation != null && isInJavaLangAnnotationPackage(annotation.annotationType()));
    }

    /**
     * Determine if the {@link Annotation} with the supplied name is defined
     * in the core JDK {@code java.lang.annotation} package.
     *
     * @param annotationType the annotation type to check
     * @return {@code true} if the annotation is in the {@code java.lang.annotation} package
     * @since 4.3.8
     */
    static boolean isInJavaLangAnnotationPackage(Class<? extends Annotation> annotationType) {
        return (annotationType != null && isInJavaLangAnnotationPackage(annotationType.getName()));
    }

    /**
     * Determine if the {@link Annotation} with the supplied name is defined
     * in the core JDK {@code java.lang.annotation} package.
     *
     * @param annotationType the name of the annotation type to check
     * @return {@code true} if the annotation is in the {@code java.lang.annotation} package
     * @since 4.2
     */
    public static boolean isInJavaLangAnnotationPackage(String annotationType) {
        return (annotationType != null && annotationType.startsWith("java.lang.annotation"));
    }

    private static void test01() {
        final Class<AnnotationTest> testClass = AnnotationTest.class;
        final Method[] methods = testClass.getDeclaredMethods();
        for (Method method : methods) {
            final NeedTest annotation = method.getAnnotation(NeedTest.class);
            if (annotation != null) {
                System.out.println("===注解" + method.getName() + " = " + annotation.value());
            } else {
                System.out.println("===未加注解" + method.getName());
            }
        }
    }
}
