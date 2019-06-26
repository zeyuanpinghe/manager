package com.apricot.woods.framework.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;

/**
 * Spring 上下文工具类
 */
public final class ContextUtil {
    private static final ContextUtil instance = new ContextUtil();

    /**
     * servlet上下文
     */
    private ServletContext servletContext;

    /**
     * spring 上下文
     */
    private ApplicationContext springContext;

    /**
     * 单例模式句柄
     * @return
     */
    public static final ContextUtil getInstance(){
        return instance;
    }

    /**
     * servletContext的getter方法
     * @return
     */
    public static ServletContext getServletContext(){
        return getInstance().servletContext;
    }

    /**
     * SpringContext的getter方法
     * @return
     */
    public static ApplicationContext getSpringContext(){
        return getInstance().springContext;
    }

    private ContextUtil(){
        servletContext = null;
        springContext = null;
    }

    /**
     * 容器或服务关闭时，清除上下文
     */
    public void cleanup(){
        servletContext = null;
        springContext = null;
    }

    /**
     * 容器启动初期化
     * @param servletContext
     */
    public void init(ServletContext servletContext){
        this.servletContext = servletContext;
        springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
    }

    public static Object getBean(String beanName){
        return getBean(beanName,(Object[]) null);
    }

    public static Object getBean(String beanName, Object... args){
        return getInstance().springContext.getBean(beanName,args);
    }

    public static <T>  T getBean(Class<T> beanClass){
        return getBean(beanClass, (Object[]) null);
    }

    public static <T>  T getBean(Class<T> beanClass, Object... args){
        return getInstance().springContext.getBean(beanClass,args);
    }

    public static void setSpringContext(ApplicationContext context){
        getInstance().springContext = context;
    }
}
