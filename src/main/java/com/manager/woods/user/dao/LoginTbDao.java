package com.apricot.woods.user.dao;

import com.apricot.woods.user.model.LoginTb;

public interface LoginTbDao {

    /**
     * 登录日志保存
     * @param loginTb
     * @return
     */
    Integer saveLogin(LoginTb loginTb);
}
