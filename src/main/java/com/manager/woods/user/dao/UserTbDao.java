package com.manager.woods.user.dao;

import com.manager.woods.user.model.UserTb;

import java.util.List;
import java.util.Map;

public interface UserTbDao {

    /**
     * 保存用户注册信息
     * @param userTb
     * @return
     */
    int saveUser(UserTb userTb);

    /**
     * 根据token查询用户信息
     * @param token
     * @return
     */
    List<UserTb> selectIdByToken(String token);

    /**
     * 保存用户登录时生成的token根据id
     * @param userTb
     * @return
     */
    int updateTokenById(UserTb userTb);

    /**
     * 根据手机号码查询用户列表
     * @param phoneNum
     * @return
     */
    List<UserTb> selectUserByPhone(String phoneNum);

    /**
     * 根据邮箱查询用户列表
     * @param emial
     * @return
     */
    List<UserTb> selectUserByEmail(String emial);

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
     * 根据用户ID查询用户信息
     * @param usrId
     * @return
     */
    UserTb selectUserById(Integer usrId);

    /**
     * 清除token
     * @param userId
     * @return
     */
    Integer rmTokenById(Integer userId);

    /**
     * 查询所有有效的志愿者总数
     * @return
     */
    int selectAllVolun();

    /**
     * 根据Id查询志愿者信息
     * @param usrId
     * @return
     */
    UserTb selectVolunById(Integer usrId);

    /**
     * 查询所有有效志愿者
     * @param paramMap pageNow 当前页,pageSize 页面展示数量
     * @return
     */
    List<UserTb> findAllVolun(Map paramMap);

}

