package com.seu.study.reflect;

import com.seu.study.info.Student;
import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class IntrospectorTest {
    @Test
    public void test02() throws Exception {
        Student student = new Student();
        PropertyDescriptor agePro = new PropertyDescriptor("age", Student.class);
        Method writeMethod = agePro.getWriteMethod();
        writeMethod.invoke(student, 1000);

        System.out.println(student);
    }

        @Test
    public void test01() throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(Student.class);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            System.out.println(propertyDescriptor);
        }
    }
}
