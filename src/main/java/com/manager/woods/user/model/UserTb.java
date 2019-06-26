package com.apricot.woods.user.model;


import com.apricot.woods.framework.model.BaseModel;
import com.apricot.woods.reserv.model.ReservTb;
import lombok.Data;

import java.util.List;

@Data
public class UserTb extends BaseModel {

  private String phoneNumber;
  private String email;
  private String password;
  private Integer roleId;
  private String named;
  private String token;
  private String roleLvel;
  private String imei;

}
