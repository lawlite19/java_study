package com.seu.study.annotation.framework.domain;

import com.seu.study.annotation.framework.annotation.AnnotationBean;
import com.seu.study.annotation.framework.annotation.AnnotationFieldBean;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@AnnotationBean(value = "t_user")
public class User {
    private Integer id;

    private String name;

    private Integer age;

    @AnnotationFieldBean(value = "birth_day")
    private Date birthday;
}
