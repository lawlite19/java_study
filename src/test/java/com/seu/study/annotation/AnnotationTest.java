package com.seu.study.annotation;

import org.junit.Test;

import java.lang.reflect.Field;

public class AnnotationTest {


    @Test
    public void test02() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.seu.study.annotation.AnnotationTest");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            AnnotationFieldBean annotation = field.getAnnotation(AnnotationFieldBean.class);
            if (annotation != null) {
                System.out.println(field.getName() + " : " + annotation.value());
            }
        }
    }

        @Test
    public void test01() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.seu.study.annotation.AnnotationTest");

        boolean annotationPresent = clazz.isAnnotationPresent(AnnotationBean.class);
        System.out.println(annotationPresent);

        if (annotationPresent) {
            AnnotationBean annotationBean = clazz.getAnnotation(AnnotationBean.class);
            if (annotationBean != null) {
                System.out.println(annotationBean);
                System.out.println(annotationBean.table() + " : " + annotationBean.from());
            }
        }
    }
}
