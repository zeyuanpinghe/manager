package com.apricot.woods.user.controller;

import com.apricot.woods.framework.common.Result;
import com.apricot.woods.framework.handler.MyException;
import com.apricot.woods.framework.utils.HttpRequestUtil;
import com.apricot.woods.framework.utils.ResultUtil;
import com.apricot.woods.framework.utils.StringCode;
import com.apricot.woods.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/***
 * 注册接口
 */
@RequestMapping("/reg")
@RestController
public class RegController{

    private static final Logger logger = LoggerFactory.getLogger(RegController.class);

    @Autowired
    private UserService userService;

    private String apiName = "/reg/add";
    /**
     * 用户新增接口
     * @param apiKey 接口校验Key
     * @param userName 用户名（手机号码/邮箱帐号）
     * @param flag 标识（手机号码：p，邮箱：e）
     * @param password 密码（真实密码+cot生成aes加密串）
     * @return
     */
//    @RequestMapping(value="add", method = RequestMethod.POST)
    public Result<String> addRoles(@RequestParam(required = true,value="apiKey") String apiKey,
                                     @RequestParam(required = true,value="username") String userName,
                                     @RequestParam(required = true,value="flag") String flag,
                                     @RequestParam(required = true,value="password") String password
    ){
        if(userService.regVerityAndBuildUserInfo(apiKey,userName,flag, StringCode.decode(password),apiName) > 0)
            return ResultUtil.ok(null);
        else throw new MyException("105","registration failed!");
    }

    /**
     * 用户新增接口
     * @return
     */
    @RequestMapping(value="add", method = RequestMethod.POST)
    public Result<String> addRoles1(HttpServletRequest httpRequest
                                    ){
        Map<String,String> requestMap = HttpRequestUtil.commonHttpRequestParamConvert(httpRequest);
        logger.info("receive AppCallHttpRequest requestMap:{}", requestMap);
        String apiKey = requestMap.get("apiKey");
        HttpRequestUtil.checkParam(apiKey,"apiKey");

        String userName = requestMap.get("username");
        HttpRequestUtil.checkParam(userName,"username");

        String flag = requestMap.get("flag");
        HttpRequestUtil.checkParam(flag,"flag");

        String password = requestMap.get("password");
        HttpRequestUtil.checkParam(password,"password");

        if(userService.regVerityAndBuildUserInfo(apiKey,userName,flag, StringCode.decode(password),apiName) > 0)
            return ResultUtil.ok(null);
        else throw new MyException("105","registration failed!");
    }


}
