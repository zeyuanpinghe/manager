package com.manager.woods.user.dao;

import com.manager.woods.user.model.LoginTb;
import com.manager.woods.user.model.LogoutTb;

public interface LogoutTbDao {

    /**
     * 登出日志保存
     * @param logoutTb
     * @return
     */
    Integer saveLogout(LogoutTb logoutTb);
}
