package com.apricot.woods.user.model;


import com.apricot.woods.framework.model.BaseModel;
import com.apricot.woods.reserv.model.ReservTb;
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
