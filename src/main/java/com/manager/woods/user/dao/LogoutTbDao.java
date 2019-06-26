package com.apricot.woods.user.dao;

import com.apricot.woods.user.model.LoginTb;
import com.apricot.woods.user.model.LogoutTb;

public interface LogoutTbDao {

    /**
     * 登出日志保存
     * @param logoutTb
     * @return
     */
    Integer saveLogout(LogoutTb logoutTb);
}
