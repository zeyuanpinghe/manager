package com.manager.woods.user.controller;

import com.manager.woods.framework.common.Result;
import com.manager.woods.framework.handler.MyException;
import com.manager.woods.framework.utils.HttpRequestUtil;
import com.manager.woods.framework.utils.ResultUtil;
import com.manager.woods.framework.utils.StringCode;
import com.manager.woods.user.model.UserInfo;
import com.manager.woods.user.service.LoginService;
import com.manager.woods.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@RequestMapping("/login")
@RestController
public class LoginController{

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

    private String apiName = "/login/verity";

    private String adminApiName = "/login/adminVerity";
    /**
     * 用户登录接口
     * @return
     */
    @RequestMapping(value="verity", method = RequestMethod.POST)
    public Result<UserInfo> verity(HttpServletRequest httpRequest
    ){
        Map<String,String> requestMap = HttpRequestUtil.commonHttpRequestParamConvert(httpRequest);
        logger.info("receive AppCallHttpRequest requestMap:{}", requestMap);
        String apiKey = requestMap.get("apiKey");
        HttpRequestUtil.checkParam(apiKey,"apiKey");

        String userName = requestMap.get("username");
        HttpRequestUtil.checkParam(userName,"username");

        String imei = requestMap.get("imei");
        HttpRequestUtil.checkParam(imei,"imei");

        String flag = requestMap.get("flag");
        HttpRequestUtil.checkParam(flag,"flag");

        String password = requestMap.get("password");
        HttpRequestUtil.checkParam(password,"password");

        String loginTime = requestMap.get("loginTime");
        HttpRequestUtil.checkParam(loginTime,"loginTime");

        UserInfo userInfo = loginService.checkLoginVerity(apiKey,userName,flag, StringCode.decode(password),imei,loginTime,apiName);
        if(userInfo != null) {
            return ResultUtil.ok(userInfo);
        } else
            throw new MyException("106","Login failure!");
    }

    /**
     * 管理员登录接口
     * @return
     */
    @RequestMapping(value="adminVerity", method = RequestMethod.POST)
    public Result<UserInfo> adminVerity(HttpServletRequest httpRequest
    ){
        Map<String,String> requestMap = HttpRequestUtil.commonHttpRequestParamConvert(httpRequest);
        logger.info("receive AppCallHttpRequest requestMap:{}", requestMap);
        String apiKey = requestMap.get("apiKey");
        HttpRequestUtil.checkParam(apiKey,"apiKey");

        String userName = requestMap.get("username");
        HttpRequestUtil.checkParam(userName,"username");

        String imei = requestMap.get("imei");
        HttpRequestUtil.checkParam(imei,"imei");

        String flag = requestMap.get("flag");
        HttpRequestUtil.checkParam(flag,"flag");

        String password = requestMap.get("password");
        HttpRequestUtil.checkParam(password,"password");

        String loginTime = requestMap.get("loginTime");
        HttpRequestUtil.checkParam(loginTime,"loginTime");

        UserInfo userInfo = loginService.checkAdminLoginVerity(apiKey,userName,flag, StringCode.decode(password),imei,loginTime,adminApiName);
        if(userInfo != null) {
            return ResultUtil.ok(userInfo);
        } else
            throw new MyException("106","Login failure!");
    }
}
