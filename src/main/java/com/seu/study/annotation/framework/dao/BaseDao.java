package com.seu.study.annotation.framework.dao;

import java.io.Serializable;

public interface BaseDao {
    /**
     * insert into t(...) values(...)
     *
     * @param t   t
     * @param <T> 泛型
     * @return Serializable
     */
    <T> Serializable save(T t);
}
