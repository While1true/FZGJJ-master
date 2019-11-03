package com.lecheng.hello.fzgjj.Utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by vange on 2017/12/27.
 */

public class ReflectUtils {

    public static Field getField(Class clazz, String fieldName) {
        Field field = null;
        try {
            field = clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            try {
                field = clazz.getSuperclass().getDeclaredField(fieldName);
            } catch (NoSuchFieldException e1) {
                e1.printStackTrace();
            }
        }
        if (field != null) {
            field.setAccessible(true);
        }
        return field;
    }

    public static <T> T getFieldValue(Object object, String fieldName) {
        Field field = getField(object.getClass(), fieldName);
        try {
            return (T) field.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setFieldValue(Object object, String fieldName,Object arg){
        Field field = getField(object.getClass(), fieldName);
        try {
            field.set(object,arg);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static Method getMethod(Class clazz,String methodName,Class<?>...parameter){
        Method method=null;
        try {
            method=clazz.getDeclaredMethod(methodName,parameter);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            try {
                method=clazz.getSuperclass().getDeclaredMethod(methodName,parameter);
            } catch (NoSuchMethodException e1) {
                e1.printStackTrace();
            }
        }
        if(method!=null){
            method.setAccessible(true);
        }
        return method;
    }

    public static <T>T invokeMethod(Object object,String methodName,Class<?>[]parameter,Object... args){
        Method method = getMethod(object.getClass(), methodName, parameter);
        try {
           return (T) method.invoke(object, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Constructor getConstruct(Class clazz,Class<?>...clazzs){
        try {
            return clazz.getDeclaredConstructor(clazzs);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
