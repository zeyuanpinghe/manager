package com.apricot.woods.framework.utils;

import java.util.Map;

public class PageAndSizeUtil {

    public static Map getPageMap(String pageNow, String pageSize, Map paramMap){
        if(paramMap == null)
            return null;
        int pages=0;            //待显示页面
        int limit=10;            //每页显示记录条数

        if (pageNow == null) {
            pages = 1;
        } else {
            try{
                pages = Integer.parseInt(pageNow);
            }catch(Exception e){
                pages = 1;
            }
            if (pages < 1){
                pages = 1;
            }
        }

        if (pageSize == null) {
            limit = 10;
        } else {
            try{
                limit = Integer.parseInt(pageSize);
            }catch(Exception e){
                limit = 10;
            }
            if (limit < 1){
                limit = 10;
            }
        }

        int startRow = (pages-1)*limit;

        paramMap.put("startRow",startRow);
        paramMap.put("limit",limit);

        return paramMap;
    }
}
