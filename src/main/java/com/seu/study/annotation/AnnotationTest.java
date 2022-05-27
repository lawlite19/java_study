package com.seu.study.annotation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AnnotationBean(table = "t_ods_test")
public class AnnotationTest {

    @AnnotationFieldBean("id")
    private Integer id;

    @AnnotationFieldBean("name")
    private String name;

    @AnnotationFieldBean("sex")
    private String sex;
}
