package com.test.proxy;

public class StaticProxy implements IUserDAO{

    private IUserDAO target;

    public StaticProxy(IUserDAO target) {
        this.target = target;
    }

    @Override
    public void save() {
        System.out.println("执行前");
        target.save();
        System.out.println("执行后");
    }
}
