package com.manager.woods.user.model;


import com.manager.woods.framework.model.BaseModel;
import lombok.Data;

import java.util.Date;

@Data
public class LogoutTb extends BaseModel {

  private String token;
  private Integer userId;
  private String phoneNum;
  private String email;
  private Date logoutTime;


}
