package com.seu.study.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 作用在什么地方
@Target(ElementType.FIELD)

// 运行的时候加载都
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationFieldBean {
    String value();
}
