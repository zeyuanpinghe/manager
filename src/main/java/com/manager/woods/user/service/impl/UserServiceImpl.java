package com.apricot.woods.user.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.apricot.woods.framework.handler.MyException;
import com.apricot.woods.framework.utils.PageAndSizeUtil;
import com.apricot.woods.reserv.model.ReservTb;
import com.apricot.woods.user.dao.LogoutTbDao;
import com.apricot.woods.user.dao.UserTbDao;
import com.apricot.woods.user.model.LogoutTb;
import com.apricot.woods.user.model.UserInfo;
import com.apricot.woods.user.model.UserTb;
import com.apricot.woods.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends UserBaseService implements UserService {

    @Autowired
    private UserTbDao userTbDao;

    @Autowired
    private LogoutTbDao logoutTbDao;

    @Override
    public UserTb selectUserByPhone(String phoneNum) {
        List<UserTb> userTbList = userTbDao.selectUserByPhone(phoneNum);
        if(userTbList != null && userTbList.size()>0){
           return userTbList.get(0);
        }
        return null;
    }
    @Override
    public UserTb selectUserByEmail(String emial) {
        List<UserTb> userTbList = userTbDao.selectUserByEmail(emial);
        if(userTbList != null && userTbList.size()>0){
            return userTbList.get(0);
        }
        return null;
    }

//    @Override
    public int saveUser(UserTb userTb) {
        return userTbDao.saveUser(userTb);
    }

    @Override
    public UserTb selectIdByToken(String token) {
        List<UserTb> userTbList = userTbDao.selectIdByToken(token);
        if(userTbList != null && userTbList.size()>0){
            return userTbList.get(0);
        }
        return null;
    }

    @Override
    public UserInfo findUserInfo(UserTb userTb) {
        if(userTb == null){
            return null;
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setToken(userTb.getToken());
        userInfo.setRoleLvel(userTb.getRoleLvel());
        userInfo.setPhoneNumber(userTb.getPhoneNumber());
        userInfo.setEmail(userTb.getEmail());
        userInfo.setNamed(userTb.getNamed());
        userInfo.setId(userTb.getId());

        return userInfo;
    }

    @Override
    public Integer regVerityAndBuildUserInfo(String apiKey,String userName,String flag,String password,String apiName) {
        UserTb userTb = new UserTb();
        if(!verityApiKey(apiName,apiKey))
            throw new MyException("101","Api permission check failed!");

        switch (flag){
            case "p":
                UserTb userTbPhone =  selectUserByPhone(userName);
                if(userTbPhone != null){
                    throw new MyException("102","Mobile phone number registered!");
                }
                userTb.setPhoneNumber(userName);
                break;
            case "e":
                UserTb userTbEmail = selectUserByEmail(userName);
                if(userTbEmail != null){
                    throw new MyException("103","Email account has been registered!");
                }
                userTb.setEmail(userName);
                break;
            default:  throw new MyException("104","flag missing!");
        }
        try {
            String dePassWord = dePasswordWithAes(password);
            userTb.setPassword(buildPasswordWithAes(dePassWord));
        }catch (Exception e){
            throw new MyException("105","Registration failed!");
        }

        return saveUser(userTb);
    }

    @Override
    public Integer updateNamedByUserId(Integer userId, String named) {
        return userTbDao.updateNamedByUserId(userId, named);
    }

    @Override
    public Integer updatePasswordByUserId(Integer userId, String password, String newPassword) {

        String enOldPassword;
        String enNewPassword;
        try {
            enOldPassword = buildPasswordWithAes(dePasswordWithAes(password));
            enNewPassword = buildPasswordWithAes(dePasswordWithAes(newPassword));
        }catch (Exception e){
            throw new MyException("105", "Password error");
        }
        if(StringUtils.isEmpty(enOldPassword)||StringUtils.isEmpty(enNewPassword)){
            throw new MyException("105", "Password error");
        }
        UserTb userTb = userTbDao.selectUserById(userId);
        if(userTb != null){
            try {
                if (!verityPassword(userTb.getPassword(), password))
                    throw new MyException("105", "Password error!");
            }catch (Exception e){
                throw new MyException("105", "Password error");
            }
        }else{
            throw new MyException("106", "Account exception!");
        }

        return userTbDao.updatePasswordByUserId(userId, enOldPassword, enNewPassword);
    }

    @Transactional
    @Override
    public void logoutByUserId(Integer userId,String token) {

        LogoutTb logoutTb = new LogoutTb();
        logoutTb.setToken(token);
        logoutTb.setUserId(userId);

        userTbDao.rmTokenById(userId);
        logoutTbDao.saveLogout(logoutTb);
    }

    @Override
    public List<UserTb> findAllVolun(String pageNow, String pageSize) {

        // 获取当前页和页面个数限制
        Map paramMap = PageAndSizeUtil.getPageMap(pageNow,pageSize,new HashMap());
        //查询当前页面数据
        List<UserTb> userTbList = userTbDao.findAllVolun(paramMap);

        return userTbList;
    }

    @Override
    public int selectAllVolun() {
        return userTbDao.selectAllVolun();
    }


}
