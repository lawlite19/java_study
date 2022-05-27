package com.seu.study.reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;

public class ReflectionConstructorTest {
    @Test
    public void test02() throws Exception {
        Class<?> clazz = Class.forName("com.seu.study.info.Student");

        Constructor<?> constructor = clazz.getConstructor(String.class, String.class, Integer.class);

        Object object = constructor.newInstance("test01", "male", 20);

        System.out.println(object);
    }

        @Test
    public void test01() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.seu.study.info.Student");

        Constructor<?>[] constructors = clazz.getConstructors();

        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }
    }
}
