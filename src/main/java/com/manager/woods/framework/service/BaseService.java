package com.manager.woods.framework.service;

import com.manager.woods.framework.handler.MyException;
import com.manager.woods.user.model.UserTb;
import com.manager.woods.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BaseService {

    @Autowired
    private UserService userServicei;

    /**
     * 校验token并获取用户ID
     * @param token
     * @return
     */
    public Integer verityTokenAndgetUserId(String token){
        if(token == null || "".equals(token)){
            throw new MyException("100","Token check failure!");
        }
        UserTb userTb = userServicei.selectIdByToken(token);
        if(userTb == null || userTb.getId() == null){
            throw new MyException("100","Token check failure!");
        }
        Integer userId = userTb.getId();

        if(userId == null){
            throw new MyException("101","Illegal access!");
        }
        return userId;
    }

}
