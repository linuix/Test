package com.model;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by uixli on 2018/5/2.
 */

public class ObjectPoolFactory {

    //定义一个创建对象的方法
    //该方法只要传入一个字符串类名，程序可以根据该类名生成Java对象
    public static Object BUILD_OBJECT(String clazzName,Class []classes,Object ...parameter) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> clazz = Class.forName(clazzName);
        Constructor constructor = clazz.getConstructor(classes);
        Object instance =  constructor.newInstance(parameter);
        return instance;
    }


    public static void main(String arg[]){
        try {
            Object s = BUILD_OBJECT("java.lang.String",new Class[]{String.class},"abc");
            System.out.println(s);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
