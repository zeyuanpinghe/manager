package com.manager.woods.user.model;


import com.manager.woods.framework.model.BaseModel;
import com.manager.woods.reserv.model.ReservTb;
import lombok.Data;

import java.util.List;

@Data
public class UserInfo extends BaseModel {

  private String phoneNumber;
  private String named;
  private String password;
  private String email;
  private String roleLvel;
  private Integer roleId;
  private String token;
  private List<ReservTb> reservTbList;

}
