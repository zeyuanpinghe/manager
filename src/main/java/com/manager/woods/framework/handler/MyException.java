package com.apricot.woods.framework.handler;

import lombok.Data;
import lombok.Getter;

@Data
public class MyException extends RuntimeException {

    public MyException(String code, String msg) {

        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;


    // getter & setter
}
