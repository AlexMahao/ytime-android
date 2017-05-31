package com.spearbothy.ytime.netimpl;

import java.lang.reflect.Field;
import java.util.HashMap;


/**
 * 系统工具类。
 *
 * @author zhangguangguang
 */
public abstract class YTimeUtils {

    public  static  HashMap<String, String> object2Map(Object obj) {
        HashMap<String, String> map = new HashMap<>();

        Class cls = obj.getClass();

        Field[] fields = cls.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                //System.out.println("字段名："+field.getName());
                Object o = field.get(obj);
                if (o != null)
                    map.put(field.getName(), o.toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

}


