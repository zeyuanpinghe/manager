package com.manager.woods.framework.utils;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.manager.woods.framework.handler.MyException;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

/**
 * HttpRequest格式转换工具类
 * Created by czYuan on 2019/04/02.
 */
public class HttpRequestUtil {

    /**
     * 通用请求格式转换
     * @param httpServletRequest
     * @return
     */
    public static Map<String, String> commonHttpRequestParamConvert(HttpServletRequest httpServletRequest) {
        Map<String, String> params = new HashMap<>();
        try {
            Map<String, String[]> requestParams = httpServletRequest.getParameterMap();
            if (requestParams != null && !requestParams.isEmpty()) {
                requestParams.forEach((key, value) -> params.put(key, value[0]));
            } else {
                StringBuilder paramSb = new StringBuilder();
                try {
                    String str = "";
                    BufferedReader br = httpServletRequest.getReader();
                    while((str = br.readLine()) != null){
                        paramSb.append(str);
                    }
                } catch (Exception e) {
                    System.out.println("httpServletRequest get requestbody error, cause : " + e);
                }
                if (paramSb.length() > 0) {
                    JSONObject paramJsonObject = JSON.parseObject(paramSb.toString());
                    if (paramJsonObject != null && !paramJsonObject.isEmpty()) {
                        paramJsonObject.forEach((key, value) -> params.put(key, String.valueOf(value)));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("commonHttpRequestParamConvert error, cause : " + e);
        }
        return params;
    }

    /**
     * 判断必传参数是否为空
     * @param param
     * @param paramName
     */
    public static void checkParam(String param,String paramName){
        if(StringUtils.isEmpty(param))
            throw new MyException("400","Required String parameter '"+paramName+"' is not present");
    }
}
