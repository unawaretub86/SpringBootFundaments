package com.fundamentos.springboot.fundamentos.bean;

public class MyBeanWithDependencyImplement implements MyBeanWithDependecy
{
    private MyOperation myOperation;

    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependecy() {
        int number = 1;
        System.out.println(myOperation.sum(1));
        System.out.println("Hi from implementation from bean with dependency");
    }
}
