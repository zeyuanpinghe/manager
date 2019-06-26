package com.apricot.woods.framework.exception;

import com.apricot.woods.framework.constans.ErrorCode;
import com.apricot.woods.framework.utils.MessageUtil;

public class CommonException extends Exception {

    protected ErrorCode code;

    public CommonException(ErrorCode code,Object... params){
        super(MessageUtil.getMessage(code.name(),params),null);
    }

    public CommonException(ErrorCode code,Throwable cause, Object... params){
        super(MessageUtil.getMessage(code.name(),params),cause);
    }

    public ErrorCode getCode(){
        return code;
    }
}
