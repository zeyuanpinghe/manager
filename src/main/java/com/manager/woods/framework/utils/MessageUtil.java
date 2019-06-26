package com.apricot.woods.framework.utils;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.util.Locale;

/**
 * 国际化属性加载器
 */
public final class MessageUtil {
    private MessageUtil(){

    }

    public static final ResourceBundleMessageSource message = new ResourceBundleMessageSource();

    static{
        message.setBasenames("message/error", "message/message");
        message.setDefaultEncoding("UTF-8");
    }

    /**
     * 返回消息信息
     * @param code
     * @param params
     * @return
     */
    public static String getMessage(String code,Object... params){
        return message.getMessage(code,params, Locale.SIMPLIFIED_CHINESE);
    }


    /**
     * 返回消息信息
     * @param code
     * @param params
     * @return
     */
    public static String getMessage(Locale locale, String code,Object... params){
        return message.getMessage(code,params, locale);
    }


}
