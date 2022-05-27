package com.seu.study.annotation.framework.dao.impl;

import com.seu.study.annotation.framework.dao.BaseDao;
import com.seu.study.annotation.framework.utils.Tools;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class BaseDaoImpl implements BaseDao {
    /**
     * insert into
     */
    @Override
    public <T> Serializable save(T t) {
        StringBuilder builder = new StringBuilder("insert into ");
        String table = Tools.getTable(t.getClass());

        builder.append(table).append(" (");

        Class<?> clazz = t.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (!field.getName().equals("id")) {
                String column = Tools.getColumn(field);
                builder.append(column).append(",");
            }
        }
        builder.deleteCharAt(builder.toString().length() - 1)
                .append(") values (");

        for (Field field : fields) {
            if (!field.getName().equals("id")) {
                builder.append("?,");
            }
        }
        builder.deleteCharAt(builder.toString().length() - 1).append(")");

        System.out.println(builder.toString());
        try {
            int index = 1;
//            pstmt = mysql数据库的prepareStatment();
            // 设置对应的值，调用t的get方法获取值
            for (Field field : fields)
                if (!field.getName().equals("id")) {
                    String getMethod = Tools.getMethod(field);
                    Method method = clazz.getDeclaredMethod(getMethod);
                    Object obj = method.invoke(t);
//                    pstmt.setObject(index++, obj);

                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
