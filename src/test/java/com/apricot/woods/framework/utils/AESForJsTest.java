package com.apricot.woods.framework.utils;

public class AESForJsTest {


    static String data = "asdf@123456780123456";
    static String md5key="apricot_woods";
    static String key = "apricot_woods";  //16位

    public static void main(String args[]) throws Exception {

//        String en1 = AESForJs.encryptAES(data,key);
        String de1 = AESForJs.decryptAES("YScXjR4tm+wjnpCcI8lPmqErjR+fsYQomcWs74DyQH0=",key);
//        System.out.println(en1);
        System.out.println(de1);
    }


}