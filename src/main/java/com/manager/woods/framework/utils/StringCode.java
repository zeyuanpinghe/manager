package com.apricot.woods.framework.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class StringCode {

    /**
     * 特殊字符转码
     * @param param
     * @return
     */
    public static String encode(String param){
        if(param!=null&&!param.trim().equals("")){
            try{
                param= URLEncoder.encode(param,"UTF-8");
            }catch(UnsupportedEncodingException e){
                e.printStackTrace();
            }
        }
        return param;
    }

    /**
     * 特殊字符解码
     * @param param
     * @return
     */
    public static String decode(String param){
        if(param!=null&&!param.trim().equals("")){
            try{
                param=URLDecoder.decode(param,"UTF-8");
            }catch(UnsupportedEncodingException e){
                e.printStackTrace();
            }
        }
        return param;
    }
}
