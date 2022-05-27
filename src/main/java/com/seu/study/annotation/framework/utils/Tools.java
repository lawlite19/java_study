package com.seu.study.annotation.framework.utils;

import com.seu.study.annotation.framework.annotation.AnnotationBean;
import com.seu.study.annotation.framework.domain.People;
import com.seu.study.annotation.framework.annotation.AnnotationFieldBean;
import com.seu.study.annotation.framework.domain.User;

import java.lang.reflect.Field;

public class Tools {
    /**
     * 获取实体类对应的表名
     *
     * @param clazz clazz
     * @return 对应的注解信息
     */
    public static String getTable(Class<?> clazz) {
        String tableName;
        AnnotationBean annotationBean = clazz.getAnnotation(AnnotationBean.class);
        if (annotationBean != null) {
            tableName = annotationBean.value();
        } else {
            tableName = clazz.getSimpleName();
        }
        return tableName;
    }


    /**
     * 获取字段
     *
     * @param field field
     * @return 对应的注解信息
     */
    public static String getColumn(Field field) {
        String column;
        AnnotationFieldBean annotationFieldBean = field.getAnnotation(AnnotationFieldBean.class);

        if (annotationFieldBean != null) {
            column = annotationFieldBean.value();
        } else {
            column = field.getName();
        }
        return column;
    }

    /**
     * 获取field的get方法
     *
     * @param field field
     * @return getXXX
     */
    public static String getMethod(Field field) {
        String fieldName = field.getName();

        String name = fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);

        return "get" + name;
    }

    public static void main(String[] args) throws NoSuchFieldException {
        System.out.println(getTable(User.class));
        System.out.println(getTable(People.class));

        Class<User> userClass = User.class;
        Field sex = userClass.getDeclaredField("birthday");
        System.out.println(getColumn(sex));

        System.out.println(getMethod(sex));
    }
}
