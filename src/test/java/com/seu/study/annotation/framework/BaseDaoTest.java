package com.seu.study.annotation.framework;

import com.seu.study.annotation.framework.dao.impl.BaseDaoImpl;
import com.seu.study.annotation.framework.domain.User;
import org.junit.Test;

import java.util.Date;

public class BaseDaoTest {
    @Test
    public void test01() {
        BaseDaoImpl baseDao = new BaseDaoImpl();
        User user = User.builder().name("test01").age(18).birthday(new Date()).build();

        baseDao.save(user);
    }
}
