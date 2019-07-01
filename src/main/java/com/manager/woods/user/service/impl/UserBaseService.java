package com.manager.woods.user.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.manager.woods.framework.handler.MyException;
import com.manager.woods.framework.utils.AESAppUtils;
import com.manager.woods.framework.utils.AESForJs;
import com.manager.woods.framework.utils.MD5Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class UserBaseService {
    @Value("${manager.aeskey}")
    private String aeskey;

    @Value("${manager.aesJs}")
    private String aesJs;

    @Value("${manager.md5key}")
    private String md5key;

    @Value("${manager.passwdKey}")
    private String passwdKey;

    /**
     * 校验接口的apikey
     * @param apiName 接口名称
     * @param apiKey 密钥
     * @return 校验成功返回true,失败返回false;
     */
    public boolean verityApiKey(String apiName,String apiKey){
        if(apiKey == null || "".equals(apiKey)){
            throw new MyException("100","接口权限校验失败");
        }
        return apiKey.equals(AESAppUtils.aesEncrypt(apiName,aeskey));
    }

    /**
     * 生成用户登录token
     * @param username 用户手机号码或邮箱
     * @param loginTime 登录时间
     * @param imei 手机的imei
     * @param password 用户密码
     * @return 返回token
     */
    public String buildToken(String username, String loginTime, String imei, String password){
        String userData = username+loginTime+imei+password;
        return MD5Util.getMD5Code(AESAppUtils.aesEncrypt(userData,aeskey)+md5key);
    }


    /**
     * 对password进行加密
     * @param password 密码明文
     * @return 返回密码加密串
     */
    public String buildPasswordWithAes(String password){
        return AESAppUtils.aesEncrypt(password,aeskey);
    }

    /**
     * 对密码进行解密
     * @param password
     * @return
     */
    public String DePasswordWithAes(String password){
        return AESAppUtils.aesDecrypt(password,aeskey);
    }


    /**
     * 进行密码校验
     * @param originPassword 数据库中密码
     * @param verityPassword 用户提交密码
     * @return 校验成功返回true,校验失败返回false
     */
    public boolean verityPassword(String originPassword,String verityPassword)throws Exception{
        if(StringUtils.isEmpty(originPassword) || StringUtils.isEmpty(verityPassword)){
            return false;
        }

        return originPassword.equals(buildPasswordWithAes(dePasswordWithAes(verityPassword)));
    }

    /**
     * 对前端传递密码进行解密
     * @param password 原始密码
     * @return 返回密码加密串
     */
    public String dePasswordWithAes(String password){
        try {
            return AESForJs.decryptAES(password, aesJs);

        }catch (Exception e){
            throw new MyException("104","password error");
        }

    }
}