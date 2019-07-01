package com.manager.woods.user.controller;

import com.manager.woods.framework.common.Result;
import com.manager.woods.framework.handler.MyException;
import com.manager.woods.framework.service.BaseService;
import com.manager.woods.framework.utils.HttpRequestUtil;
import com.manager.woods.framework.utils.ResultUtil;
import com.manager.woods.framework.utils.StringCode;
import com.manager.woods.reserv.model.ReservTb;
import com.manager.woods.reserv.service.ReservService;
import com.manager.woods.user.model.UserInfo;
import com.manager.woods.user.model.UserTb;
import com.manager.woods.user.service.LoginService;
import com.manager.woods.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RequestMapping("/user")
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private BaseService baseService;

    @Autowired
    private ReservService reservService;
    /**
     * 用户新增接口
     * @return
     */
    @RequestMapping(value="findAllVol", method = RequestMethod.POST)
    public Result<List> verity(HttpServletRequest httpRequest
    ){
        Map<String,String> requestMap = HttpRequestUtil.commonHttpRequestParamConvert(httpRequest);
        logger.info("receive AppCallHttpRequest requestMap:{}", requestMap);
        String token = requestMap.get("token");
        HttpRequestUtil.checkParam(token,"token");

        String pageNow = requestMap.get("pageNow");

        String pageSize = requestMap.get("pageSize");

        baseService.verityTokenAndgetUserId(token);

        List<UserTb> userTbList = userService.findAllVolun(pageNow,pageSize);
        int count = userService.selectAllVolun();

        if(CollectionUtils.isEmpty(userTbList)){
            return ResultUtil.ok(userTbList,count);
        }

        List<UserInfo> userInfoTbList = new ArrayList<UserInfo>();
        for(UserTb userTb : userTbList){
            UserInfo userInfo = userService.findUserInfo(userTb);
            userInfo.setToken(null);
            userInfo.setRoleLvel(null);
            List<ReservTb> reservTbList = reservService.findMatchOrderByVold(userTb.getId());
            userInfo.setReservTbList(reservTbList);
            userInfoTbList.add(userInfo);
        }

        return ResultUtil.ok(userInfoTbList,count);
    }

}
