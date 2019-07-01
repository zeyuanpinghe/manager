package com.manager.woods.user.dao;

import com.manager.woods.user.model.RoleTb;

import java.util.List;

public interface RoleTbDao {

    /**
     * 新增角色
     * @param roleTb
     * @return
     */
    int addRoles(RoleTb roleTb);

    /**
     * 角色查询
     * @param roleId
     * @return
     */
    List<RoleTb> selectRoles(int roleId);
}
