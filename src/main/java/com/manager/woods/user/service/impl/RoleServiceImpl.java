package com.apricot.woods.user.service.impl;

import com.apricot.woods.framework.handler.MyException;
import com.apricot.woods.user.dao.RoleTbDao;
import com.apricot.woods.user.model.RoleTb;
import com.apricot.woods.user.model.UserTb;
import com.apricot.woods.user.service.RoleService;
import com.apricot.woods.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.server.InactiveGroupException;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleTbDao roleTbDao;

    @Autowired
    private UserService userService;

    @Override
    public int addRoles(RoleTb roleTb) {
        if (roleTb == null){
            return 0;
        }
        return roleTbDao.addRoles(roleTb);
    }

    @Override
    public List<RoleTb> selectRoles(int roleId) {
        List<RoleTb> roleList = roleTbDao.selectRoles(roleId);
        return roleList;
    }


}
