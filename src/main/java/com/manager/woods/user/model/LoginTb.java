package com.manager.woods.user.model;


import com.manager.woods.framework.model.BaseModel;
import lombok.Data;

import java.util.Date;

@Data
public class LoginTb extends BaseModel {

  private String imei;
  private String token;
  private Integer userId;
  private String phoneNum;
  private String email;
  private Date loginTime;


}
