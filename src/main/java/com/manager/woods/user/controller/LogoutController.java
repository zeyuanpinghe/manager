package com.apricot.woods.user.controller;

import com.apricot.woods.framework.common.Result;
import com.apricot.woods.framework.handler.MyException;
import com.apricot.woods.framework.service.BaseService;
import com.apricot.woods.framework.utils.HttpRequestUtil;
import com.apricot.woods.framework.utils.ResultUtil;
import com.apricot.woods.framework.utils.StringCode;
import com.apricot.woods.user.model.UserInfo;
import com.apricot.woods.user.service.LoginService;
import com.apricot.woods.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@RequestMapping("/logout")
@RestController
public class LogoutController {

    private static final Logger logger = LoggerFactory.getLogger(LogoutController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private BaseService baseService;

    /**
     * 用户登出接口
     * @return
     */
    @RequestMapping(value="exit", method = RequestMethod.POST)
    public Result<UserInfo> verity(HttpServletRequest httpRequest
    ){
        Map<String,String> requestMap = HttpRequestUtil.commonHttpRequestParamConvert(httpRequest);
        logger.info("receive AppCallHttpRequest requestMap:{}", requestMap);
        String token = requestMap.get("token");
        HttpRequestUtil.checkParam(token,"token");

        userService.logoutByUserId(baseService.verityTokenAndgetUserId(token),token);
        return ResultUtil.ok();
    }

}
