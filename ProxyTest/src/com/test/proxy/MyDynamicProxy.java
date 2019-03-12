package com.test.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyDynamicProxy {

    private Object target;

    public MyDynamicProxy(Object target) {
        this.target = target;
    }


    public Object proxy(){
        try {
            Method method = target.getClass().getDeclaredMethod("save",null);
            return method.invoke(target,null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }












}
