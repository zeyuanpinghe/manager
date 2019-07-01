package com.manager.woods.user.model;

import com.manager.woods.framework.model.BaseModel;
import lombok.Data;

import java.util.Date;

@Data
public class RoleTb extends BaseModel {
    private Integer id;
    private String roleLevel;
    private Date createTime;
    private Date updateTime;
    private String roleDesc;

}