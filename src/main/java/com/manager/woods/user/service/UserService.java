package com.apricot.woods.user.service;

import com.apricot.woods.user.model.UserInfo;
import com.apricot.woods.user.model.UserTb;

import java.util.List;


public interface UserService {

    /**
     * 根据手机号码查询用户列表
     * @param phoneNum
     * @return
     */
    UserTb selectUserByPhone(String phoneNum);

    /**
     * 根据邮箱查询用户列表
     * @param emial
     * @return
     */
    UserTb selectUserByEmail(String emial);

    /**
     * 保存用户注册信息
     * @param userTb
     * @return
     */
//    int saveUser(UserTb userTb);

    /**
     * 根据token查询用户信息
     * @param token
     * @return
     */
    UserTb selectIdByToken(String token);

    /**
     * 组装返回的用户信息
     * @param userTb
     * @return
     */
    UserInfo findUserInfo(UserTb userTb);

    /**
     * 注册接口权限验证，用户名和密码校验
     * @param apiKey
     * @param userName
     * @param flag
     * @param password
     * @param apiName
     * @return
     */
    Integer regVerityAndBuildUserInfo(String apiKey,String userName,String flag,String password,String apiName);

    /**
     * 根据userId修改称呼
     * @param userId
     * @param named
     * @return
     */
    Integer updateNamedByUserId(Integer userId,String named);

    /**
     * 根据userId修改密码
     * @param userId
     * @param password
     * @param newPassword
     * @return
     */
    Integer updatePasswordByUserId(Integer userId,String password,String newPassword);

    /**
     * 根据userId登出操作
     * @param userId
     */
    void logoutByUserId(Integer userId,String token);

    /**
     * 查询所有志愿者
     * @param pageNow
     * @param pageSize
     * @return
     */
    List<UserTb> findAllVolun(String pageNow, String pageSize);

    /**
     * 查询所有有效的志愿者总数
     * @return
     */
    int selectAllVolun();
}
