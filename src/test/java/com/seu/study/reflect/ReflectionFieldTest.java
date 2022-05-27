package com.seu.study.reflect;

import com.seu.study.info.Student;
import org.junit.Test;

import java.lang.reflect.Field;

public class ReflectionFieldTest {
    @Test
    public void test01() throws Exception {
        Class<?> clazz = Class.forName("com.seu.study.info.Student");

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getType() + " : " + field.getName());
        }
    }

    @Test
    public void test02() throws Exception {
        Class<?> clazz = Class.forName("com.seu.study.info.Student");

        Student student = (Student) clazz.newInstance();

        Field sexField = clazz.getDeclaredField("sex");

        if (!sexField.isAccessible()) {
            sexField.setAccessible(true);
        }
        sexField.set(student, "male");

        System.out.println(student);
    }
}
