package com.apricot.woods.framework.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.Date;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@Data
public class BaseModel {
    private Integer id;
    private Date createTime;
    private Date updateTime;
    private Integer updateId;
    private String statu;
}
