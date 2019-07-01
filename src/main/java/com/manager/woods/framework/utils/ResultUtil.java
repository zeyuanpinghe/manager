package com.manager.woods.framework.utils;

import com.manager.woods.framework.common.Result;
import com.manager.woods.framework.constans.ErrorCode;

public class ResultUtil {

    private ResultUtil(){

    }

    /**
     * 构造成功报文
     * @param data 报文内容
     * @param <T>
     * @return
     */
    public static <T> Result<T> ok(T data){
        return new Result<T>().setResult(true).setData(data);
    }

    /**
     *
     * @param data
     * @param count 总条数
     * @param <T>
     * @return
     */
    public static <T> Result<T> ok(T data,int count){
        return new Result<T>().setResult(true).setData(data).setCount(count);
    }

    /**
     * 构造成功报文
     * @return
     */
    public static <T> Result<T> ok(){
        return new Result<T>().setResult(true);
    }

    /**
     * 构造失败报文
     * @param code 错误码
     * @param errormessage 错误参数
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(ErrorCode code, String errormessage){
        //TODO
        return new Result<T>().setResult(false).setCode(code).setErrMsg(errormessage);
    }
}
