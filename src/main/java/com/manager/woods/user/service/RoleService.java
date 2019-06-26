package com.apricot.woods.user.service;

import com.apricot.woods.user.model.RoleTb;

import java.util.List;

public interface RoleService {

    /**
     * 添加角色属性
     * @param roleTb
     * @return
     */
    int addRoles(RoleTb roleTb);

    /**
     * 查询角色属性
     * @param roleId
     * @return
     */
    List<RoleTb> selectRoles(int roleId);

}
