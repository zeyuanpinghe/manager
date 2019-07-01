package com.manager.woods.user.controller;

import com.manager.woods.framework.common.Result;
import com.manager.woods.framework.handler.MyException;
import com.manager.woods.framework.service.BaseService;
import com.manager.woods.framework.utils.HttpRequestUtil;
import com.manager.woods.framework.utils.ResultUtil;
import com.manager.woods.framework.utils.StringCode;
import com.manager.woods.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RequestMapping("/setting")
@RestController
public class UserSettingController {

    private static final Logger logger = LoggerFactory.getLogger(UserSettingController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private BaseService baseService;

    @RequestMapping(value="upNamed", method = RequestMethod.POST)
    public Result<List> upNamed(HttpServletRequest httpRequest){
            Map<String,String> requestMap = HttpRequestUtil.commonHttpRequestParamConvert(httpRequest);

            logger.info("receive AppCallHttpRequest requestMap:{}", requestMap);

            String token = requestMap.get("token");
            HttpRequestUtil.checkParam(token,"token");

            String named = requestMap.get("callName");
            HttpRequestUtil.checkParam(named,"callName");

        if(userService.updateNamedByUserId(baseService.verityTokenAndgetUserId(token),named)>0){
            return ResultUtil.ok();
        }else {
            throw new MyException("101","Name change failed!");
        }
    }

    @RequestMapping(value="upPassword", method = RequestMethod.POST)
    public Result<List> changePassword(HttpServletRequest httpRequest){
        Map<String,String> requestMap = HttpRequestUtil.commonHttpRequestParamConvert(httpRequest);
        logger.info("receive AppCallHttpRequest requestMap:{}", requestMap);
        String token = requestMap.get("token");
        HttpRequestUtil.checkParam(token,"token");

        String password = requestMap.get("password");
        HttpRequestUtil.checkParam(password,"password");

        String newPassword = requestMap.get("newPassword");
        HttpRequestUtil.checkParam(password,"newPassword");

        if(userService.updatePasswordByUserId(baseService.verityTokenAndgetUserId(token),StringCode.decode(password),StringCode.decode(newPassword))>0){
            return ResultUtil.ok();
        }else {
            throw new MyException("101","Password change failed!");
        }
    }

}
