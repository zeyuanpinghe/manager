package com.apricot.woods.user.service;

import com.apricot.woods.user.model.UserInfo;

public interface LoginService {

    /**
     * check，判断apikey，用户有效性
     * @param apikey
     * @param userName
     * @param flag
     * @param password
     * @param imei
     * @param loginTime
     * @param apiName
     * @return
     */
    public UserInfo checkLoginVerity(String apikey, String userName, String flag, String password, String imei, String loginTime, String apiName);

    /**
     * 判断管理员登录
     * @param apikey
     * @param userName
     * @param flag
     * @param password
     * @param imei
     * @param loginTime
     * @param apiName
     * @return
     */
    public UserInfo checkAdminLoginVerity(String apikey, String userName, String flag, String password, String imei, String loginTime, String apiName);

}
