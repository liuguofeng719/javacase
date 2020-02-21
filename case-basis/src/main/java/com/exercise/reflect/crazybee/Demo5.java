package com.exercise.reflect.crazybee;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 * reflect 常规Api操作
 * <p>
 * 1、Class类的方法
 * forName(String className)
 * getClasses()
 * getClassLoader()
 * getConstructors()
 * getDeclaredFields()
 * getDeclaredField(String name)
 * getDeclaredMethods()
 * getSuperclass()
 * getInterfaces()
 * getGenericSuperclass()
 * getGenericInterfaces()
 * newInstance
 * <p>
 * 2、Constructor、Field、Method Api操作
 **/
public class Demo5 {

    /**
     * 1、getFields 方法只能获取公用的字段
     * 2、getDeclaredFields 方法公用和私有字段
     * 3、getMethods 只能获取公有的方法，能获取父类的所有公用方法，包括Object类中的
     * 4、getDeclaredMethods 获取公有和私有的方法，但是不能获取父类的方法
     * 5、getConstructors 获取当前类所有的public构造方法
     * 6、getDeclaredConstructors 获取当前类private和public构造方法
     */
    public static void main(String[] args) throws Exception {

//        final Class aClass = Class.forName("com.exercise.reflect.crazybee.Result");
//        getFieldMethodConstructorAndDeclaredFieldMethodConstructor(aClass);
//        System.out.println(""+genericSuperclass.getTypeName());
//        testGenericSuperClass(aClass)
//        testFieldSetGet(aClass);
//        testMethodSetGet(aClass);
//        testGenericParameterTypes();
    }

    private static void testGenericParameterTypes() throws ClassNotFoundException, NoSuchMethodException {
        // 获取方法的入参泛型类型
        final Class aClass = Class.forName("com.exercise.reflect.crazybee.Demo5");
        final Method show = aClass.getMethod("show", Map.class, int.class, String.class, List.class);
        // 获取入参的参数类型
        final Type[] genericParameterTypes = show.getGenericParameterTypes();
        for (final Type parameterType : genericParameterTypes) {
            System.out.println("parameterType = " + parameterType);
            // 判断是否参数化类型
            if (parameterType instanceof ParameterizedType) {
                final Type[] actualTypeArguments = ((ParameterizedType) parameterType).getActualTypeArguments();
                for (final Type typeArgument : actualTypeArguments) {
                    System.out.println("input parameter = " + typeArgument);
                }
            }
        }
        // 获取返回的参数类型
        final Class<?> returnType = show.getReturnType();
        System.out.println(returnType);
        final Type genericReturnType = show.getGenericReturnType();
        // 判断是否参数化类型
        if (genericReturnType instanceof ParameterizedType) {
            final Type[] typeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
            for (final Type argument : typeArguments) {
                System.out.println(argument);
            }
        }
    }

    // 测试获取方法的返回泛型参数，和输入泛型参数
    public Map<String, Character> show(Map<String, Integer> map, int age, String name, List<Float> lists) {
        return null;
    }

    private static void testMethodSetGet(Class aClass) throws NoSuchMethodException, InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException {
        final Constructor constructor = aClass.getDeclaredConstructor(null);
        constructor.setAccessible(true);
        final Result result = (Result) constructor.newInstance();
        // 获取方法
        final Method setCode = aClass.getDeclaredMethod("setCode", int.class);
        // 执行方法
        setCode.invoke(result, 11);
        // 获取方法的值
        final Method getCode = aClass.getDeclaredMethod("getCode");
        final Object invoke = getCode.invoke(result);
        System.out.println(invoke);
        // 调用私有方法
        final Method toPrintString = aClass.getDeclaredMethod("toPrintString");
        // 设置不检查访问权限
        toPrintString.setAccessible(true);
        toPrintString.invoke(result);
    }

    private static void testFieldSetGet(Class aClass) throws NoSuchMethodException, InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException, NoSuchFieldException {
        final Constructor declaredConstructor = aClass.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        final Result result = (Result) declaredConstructor.newInstance();
        // getField 不能访问私有字段 java.lang.NoSuchFieldException: code
        final Field field = aClass.getField("stackInfo");
        field.set(result, "xxxx");
        // 获取值
        System.out.println(field.get(result));
        // 获取自有的字段，访问私有属性需要设置检查权限
        final Field declaredField = aClass.getDeclaredField("code");
        declaredField.setAccessible(true);
        declaredField.set(result, 12);
        System.out.println(declaredField.getInt(result));
    }

    private static void testGenericSuperClass(Class aClass) throws NoSuchMethodException, InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException {
        // 获取父类
        final Class<? super Result> superclass = Result.class.getSuperclass();
        System.out.println("获取父类：" + superclass);
        // 获取父类的类型
        final Type genericSuperclass = Result.class.getGenericSuperclass();
        // 获取父类里面的泛型类型
        if (genericSuperclass instanceof ParameterizedType) {
            final Type type = ((ParameterizedType) (genericSuperclass)).getActualTypeArguments()[0];
            System.out.println("参数类型：" + type);
        }
        System.out.println("获取父类的类型" + genericSuperclass);
        final Constructor declaredConstructor = aClass.getDeclaredConstructor(null);
        // 设置不检查访问权限
        declaredConstructor.setAccessible(true);
        final Result newInstance = (Result) declaredConstructor.newInstance();
        final Result instance = (Result) aClass.getConstructor(int.class, boolean.class).newInstance(1, false);
        System.out.println(newInstance == instance);
    }

    private static void getFieldMethodConstructorAndDeclaredFieldMethodConstructor(Class aClass) {
        System.out.println("\ngetConstructors =======================\n");
        final Constructor[] constructors = aClass.getConstructors();
        for (final Constructor constructor : constructors) {
            System.out.println(constructor);
        }

        System.out.println("\ngetDeclaredConstructors =======================\n");
        final Constructor[] declaredConstructors = aClass.getDeclaredConstructors();

        for (final Constructor declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor);
        }

        System.out.println("\ngetFields ======================= \n");

        final Field[] fields = aClass.getFields();
        for (final Field field : fields) {
            System.out.println(field);
        }

        System.out.println("\ngetDeclaredFields =======================\n");
        final Field[] declaredFields = aClass.getDeclaredFields();
        for (final Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }

        System.out.println("\ngetMethods =======================\n");
        final Method[] methods = aClass.getMethods();
        for (final Method method : methods) {
            System.out.println(method);
        }

        System.out.println("\ngetDeclaredMethods =======================\n");
        final Method[] declaredMethods = aClass.getDeclaredMethods();
        for (final Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }
    }
}

class Page<T> {
    private List<T> data;
    private int totalSize;
    private int pageNo;
    private int pageSize;

    public Page() {

    }

    public Page(List<T> data, int pageNo, int pageSize) {
        this.data = data;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public int getTotalSize() {
        return data.size();
    }

    protected int totalPageSize() {
        return getTotalSize() % pageSize == 0 ? getTotalSize() % pageSize : getTotalSize() % pageSize + 1;
    }

    public void testClass() {
        System.out.println(" this.getClass().getGenericSuperclass() ===== " + this.getClass().getGenericSuperclass());
        System.out.println(" this.getClass()===== " + this.getClass());
    }
}

class Result extends Page<User> {

    private int code;
    private boolean success;
    private String message;
    public String stackInfo;

    private Result() {
        testClass();
    }

    public Result(int code, boolean success) {
        this.code = code;
        this.success = success;
        testClass();
    }

    public Result(int code, boolean success, String message) {
        this.code = code;
        this.success = success;
        this.message = message;
    }

    private void toPrintString() {
        System.out.println("toPrintString");
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStackInfo() {
        return stackInfo;
    }

    public void setStackInfo(String stackInfo) {
        this.stackInfo = stackInfo;
    }
}

class Request {
    private String fromSystem;
    private String version;
    private Date timestamped;
    private String parameters;
    private String reqId;

    public Request() {

    }

    public Request(String fromSystem, String version, Date timestamped, String parameters, String reqId) {
        this.fromSystem = fromSystem;
        this.version = version;
        this.timestamped = timestamped;
        this.parameters = parameters;
        this.reqId = reqId;
    }

    public Date getTimestamped() {
        return timestamped;
    }

    public void setTimestamped(Date timestamped) {
        this.timestamped = timestamped;
    }

    public String getFromSystem() {
        return fromSystem;
    }

    public void setFromSystem(String fromSystem) {
        this.fromSystem = fromSystem;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }
}


