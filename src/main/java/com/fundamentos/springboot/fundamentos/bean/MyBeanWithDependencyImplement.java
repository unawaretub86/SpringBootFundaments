package com.fundamentos.springboot.fundamentos.bean;

import org.apache.commons.logging.*;

public class MyBeanWithDependencyImplement implements MyBeanWithDependecy {

    Log LOGGER = LogFactory.getLog(MyBeanWithDependencyImplement.class);

    private MyOperation myOperation;

    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependecy() {
    LOGGER.info("We're into bean with dependency");
        int number = 1;
    LOGGER.debug("The number sent like param to dependency operation is : " + number);
        System.out.println(myOperation.sum(1));
        System.out.println("Hi from implementation from bean with dependency");
    }
}
