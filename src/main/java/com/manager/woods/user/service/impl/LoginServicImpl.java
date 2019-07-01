package com.manager.woods.user.service.impl;

import com.manager.woods.framework.handler.MyException;
import com.manager.woods.framework.utils.DateUtils;
import com.manager.woods.user.dao.LoginTbDao;
import com.manager.woods.user.dao.UserTbDao;
import com.manager.woods.user.model.LoginTb;
import com.manager.woods.user.model.UserInfo;
import com.manager.woods.user.model.UserTb;
import com.manager.woods.user.service.LoginService;
import com.manager.woods.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginServicImpl extends UserBaseService implements LoginService {

    @Autowired
    private LoginTbDao loginTbDao;

    @Autowired
    private UserService userService;

    @Autowired
    private UserTbDao userTbDao;

    @Override
    public UserInfo checkLoginVerity(String apikey,String userName,String flag,String password,String imei,String loginTime,String apiName){
        if(!verityApiKey(apiName,apikey))
            throw new MyException("101","Api permission check failed");
        UserTb userTb = null;
        LoginTb loginTb = new LoginTb();
        switch (flag){
            case "p":
                userTb =  userService.selectUserByPhone(userName);
                if(userTb == null){
                    throw new MyException("102","Mobile phone number does not exist");
                }
                loginTb.setPhoneNum(userName);
                break;
            case "e":
                userTb = userService.selectUserByEmail(userName);
                if(userTb == null){
                    throw new MyException("103","No mailbox account exists");
                }
                loginTb.setEmail(userName);
                break;
            default:  throw new MyException("104","Deletion of flag");
        }

        try {
            if (!verityPassword(userTb.getPassword(), password))
                throw new MyException("105", "Password error!");
        }catch (Exception e){
            throw new MyException("105", "Password error");
        }
        loginTb.setImei(imei);

       return loginSave(userTb,loginTb,loginTime,buildToken(userName, loginTime,imei,password));
    }


    /**
     * 更新用户表登录token，保存登录记录到日志表
     * @param userTb
     * @param loginTb
     * @param loginTime
     * @param token
     * @return
     */
    @Transactional
    public UserInfo loginSave(UserTb userTb, LoginTb loginTb, String loginTime,String token) {
        userTb.setToken(token);
        loginTb.setToken(token);
        loginTb.setUserId(userTb.getId());
        loginTb.setLoginTime(DateUtils.strToDateLong(loginTime));
        if (userTbDao.updateTokenById(userTb) > 0 && loginTbDao.saveLogin(loginTb) > 0) {
            UserInfo userInfo = userService.findUserInfo(userTb);
            return userInfo;
        }
        return null;
    }

    @Override
    public UserInfo checkAdminLoginVerity(String apikey,String userName,String flag,String password,String imei,String loginTime,String apiName){
        if(!verityApiKey(apiName,apikey))
            throw new MyException("101","Api permission check failed");
        UserTb userTb = null;
        LoginTb loginTb = new LoginTb();
        switch (flag){
            case "p":
                userTb =  userService.selectUserByPhone(userName);
                if(userTb == null){
                    throw new MyException("102","Mobile phone number does not exist");
                }
                loginTb.setPhoneNum(userName);
                break;
            case "e":
                userTb = userService.selectUserByEmail(userName);
                if(userTb == null){
                    throw new MyException("103","No mailbox account exists");
                }
                loginTb.setEmail(userName);
                break;
            default:  throw new MyException("104","Deletion of flag");
        }
        if(!userTb.getRoleLvel().contains("ADMIN")){
            throw new MyException("106","Not the administrator");
        }
        try {
            if (!verityPassword(userTb.getPassword(), password))
                throw new MyException("105", "Password error!");
        }catch (Exception e){
            throw new MyException("105", "Password error");
        }
        loginTb.setImei(imei);

        return loginSave(userTb,loginTb,loginTime,buildToken(userName, loginTime,imei,password));
    }
}
