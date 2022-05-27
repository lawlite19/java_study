package com.seu.study.reflect;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflectionClassTest {
    @Test
    public void test04() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<String> vals = new ArrayList<>();
        vals.add("test1");
        vals.add("test2");

        Class<?> clazz = vals.getClass();

        Method method = clazz.getDeclaredMethod("add", Object.class);
        method.invoke(vals, 1000);

        System.out.println();
    }

    @Test
    public void test03() {
        Class<String> clazz = String.class;
        System.out.println(clazz);
    }

    @Test
    public void test02() throws ClassNotFoundException {
        String val = "test";
        Class<? extends String> aClass = val.getClass();
        System.out.println(aClass);
    }


    @Test
    public void test01() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("java.lang.Object");
        System.out.println(clazz);

        System.out.println("-------");
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }

        System.out.println("-------");
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
    }
}
