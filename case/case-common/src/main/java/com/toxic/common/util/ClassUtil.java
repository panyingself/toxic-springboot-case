package com.toxic.common.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Title:
 * Description:
 *
 * @author py
 * @date 2018/7/19 下午3:57.
 */
public class ClassUtil {
    /**
     * Method:
     * Description: 反射获取指定类，指定字段的属性值
     * @Author panying
     * @Data 2018/7/19 下午3:58
     * @param provide
    * @param property
     * @return java.lang.Object
     */
    public static Object getProperties(Object provide, String property) {

        Object tempValue = null;
        Method[] mm = provide.getClass().getMethods();

        for(int i = 0; i < mm.length; ++i) {
            if(mm[i].getName().toUpperCase().equals("GET" + property.toUpperCase())) {
                Method cm = mm[i];

                try {
                    Object args = null;
                    tempValue = cm.invoke(provide, (Object[])args);
                } catch (IllegalAccessException var7) {
                    ;
                } catch (IllegalArgumentException var8) {
                    ;
                } catch (InvocationTargetException var9) {
                    ;
                }
                break;
            }
        }

        return tempValue;
    }
    /**
     * Method:
     * Description: 反射设置指定类，指定字段的属性值
     * @Author panying
     * @Data 2018/7/19 下午3:58
     * @param provide
    * @param property
     * @return java.lang.Object
     */
    public static Object setProperties(Object provide, String property,Object val) {
        Method[] mm = provide.getClass().getMethods();
        //加上这段代码会初始化bean
//        Object object = provide.getClass().newInstance();
        for(int i = 0; i < mm.length; ++i) {
            if(mm[i].getName().toUpperCase().equals("SET" + property.toUpperCase())) {
                Method cm = mm[i];
                try {
                    cm.invoke(provide, val);
                } catch (IllegalAccessException var7) {
                    ;
                } catch (IllegalArgumentException var8) {
                    ;
                } catch (InvocationTargetException var9) {
                    ;
                }
                break;
            }
        }

        return provide;
    }
}
