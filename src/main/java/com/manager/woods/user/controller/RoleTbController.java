package com.manager.woods.user.controller;

import com.manager.woods.framework.common.Result;
import com.manager.woods.framework.constans.ErrorCode;
import com.manager.woods.framework.handler.MyException;
import com.manager.woods.framework.utils.ResultUtil;
import com.manager.woods.user.model.RoleTb;
import com.manager.woods.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResultUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/role")
@RestController
public class RoleTbController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value="add", method = RequestMethod.POST)
    public Result<String> addRoles(@RequestBody RoleTb roleTb){
        return ResultUtil.ok(String.valueOf(roleService.addRoles(roleTb)));
    }

    @RequestMapping(value="find", method = RequestMethod.POST)
    public Result<List> findRoles(@RequestParam(name = "id") int id){
//        throw new MyException("101","系统异常");
        return ResultUtil.ok(roleService.selectRoles(id));
    }
}
