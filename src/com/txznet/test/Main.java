package com.txznet.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class Main {

    public static void main(String[] args) {
	// write your code here
        DLoad();
        try {
//            Class c = ClassLoader.getSystemClassLoader().loadClass("com.txznet.test.MyClassLoader");

            Class c = new MyClassLoader().findClass("com.txznet.test.ClassTest");
            if (ClassTest.class == c){
                System.out.println("ttttttttttttttt");
            }
            Object obj = c.newInstance();
            Method method = c.getDeclaredMethod("test");

            method.invoke(obj);


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



    public static void DLoad(){
        // 动态加载jar
        try {
            // 包路径定义
            URL urls = new URL("file:/D:\\com\\txznet\\test\\Test.jar");
            //GetPI.class
            URLClassLoader urlLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
            Class<URLClassLoader> sysclass = URLClassLoader.class;
            Method method = sysclass.getDeclaredMethod("addURL", new Class[]{URL.class});
            method.setAccessible(true);
            method.invoke(urlLoader, urls);
            /*
             * 实例化一个对象 ，这个类(yerasel.GetPI)可以是串行化传来的多个类的一个，运行时才知道需要哪个类
             * 或者从xml 配置文件中获得字符串
             * 例如
             * String className = readfromXMlConfig;//从xml 配置文件中获得字符串
             * class c = Class.forName(className);
             * factory = (ExampleInterface)c.newInstance();
             * 上面代码已经不存在类名称，它的优点是，无论Example类怎么变化，上述代码不变，
             * 甚至可以更换Example的兄弟类Example2 , Example3 , Example4……，只要他们继承ExampleInterface就可以。
             */
            // 输入类名
            String className = "com.txznet.test.ClassTest";
            Class<?> tidyClass = urlLoader.loadClass(className);
            Object obj = tidyClass.newInstance();
            // 被调用函数的参数
            Class[] parameterTypes = {};
            Method method2 = tidyClass.getDeclaredMethod("test", parameterTypes);
            method2.invoke(obj);

        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }



}
