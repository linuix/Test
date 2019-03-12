package com.test.proxy;

public class UserDAO implements IUserDAO
{
    @Override
    public void save() {
        System.out.println("do save");
    }
}
