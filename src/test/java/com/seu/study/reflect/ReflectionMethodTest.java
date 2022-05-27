package com.seu.study.reflect;

import org.junit.Test;

import java.lang.reflect.Method;

public class ReflectionMethodTest {
    @Test
    public void test01() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.seu.study.info.Student");

        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }
    }

    @Test
    public void test02() throws Exception {
        Class<?> clazz = Class.forName("com.seu.study.info.Student");

        Object object = clazz.newInstance();

        Method method = clazz.getMethod("setName", Class.forName("java.lang.String"));
        method.invoke(object, "name01");

        System.out.println(object);
    }
}
