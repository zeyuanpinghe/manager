package com.manager.woods.framework.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringCodeTest {

    @Test
    public void deCode(){
        String de = StringCode.decode("YScXjR4tm+wjnpCcI8lPmqErjR+fsYQomcWs74DyQH0=");
        System.out.println(de);
    }
}