package com.manager.woods.framework.common;


import com.manager.woods.framework.constans.ErrorCode;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Result<T>  implements Serializable {

    private boolean result;

    private ErrorCode code;

    private String errMsg;

    private int count;

    private T data;

    public boolean isResult() {
        return result;
    }

    public Result<T> setResult(boolean result) {
        this.result = result;
        return this;
    }

    public ErrorCode getCode() {
        return code;
    }

    public Result setCode(ErrorCode code) {
        this.code = code;
        return this;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public Result setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }
    public int getCount() {
        return count;
    }

    public Result setCount(int count) {
        this.count = count;
        return this;
    }
}
