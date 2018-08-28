package com.example.cache;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/8/17 下午3:31
 * @see jdk 1.7
 **/
public class TestStatic {
    private String name;
    private static volatile TestStatic testStatic;

    public static TestStatic getInstan() {
        if (testStatic == null) {
            testStatic = new TestStatic();
            return testStatic;
        } else {
            return testStatic;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {

        final TestStatic instan = TestStatic.getInstan();
        instan.setName("ewe");
        final TestStatic instan1 = TestStatic.getInstan();
        instan1.setName("ewew");
        System.out.println(instan);
        System.out.println(instan1);
        System.out.println(instan.getName());
        System.out.println(instan1.getName());
    }
}
