package com.test.proxy;

public class TestMain {

    public static void main(String ...arg){

        //静态代理
        IUserDAO userDAO = new UserDAO();
//        IUserDAO staticProxy = new StaticProxy(userDAO);
//        staticProxy.save();

        //动态代理
        DynamicProxy dynamicProxy = new DynamicProxy(userDAO);
        IUserDAO dynamicUser = (IUserDAO) dynamicProxy.getProxyInstance();
        System.out.println(dynamicUser.getClass().getName());
        dynamicUser.save();


//        MyDynamicProxy myDynamicProxy = new MyDynamicProxy(userDAO);
//        myDynamicProxy.proxy();

    }

}
