package com.seu.study.reflect;

import com.seu.study.info.Student;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class BeanUtilsTest {

    @Test
    public void test01() throws InvocationTargetException, IllegalAccessException {
        Student student = new Student();

        BeanUtils.setProperty(student, "sex", "male");

        System.out.println(student);
    }

    @Test
    public void test02() throws InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        map.put("sex", "male");
        map.put("name", "test01");
        map.put("age", 10);

        Student student = new Student();

        BeanUtils.populate(student, map);

        System.out.println(student);
    }
}
