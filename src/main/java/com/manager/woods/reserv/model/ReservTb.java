package com.apricot.woods.reserv.model;

import com.apricot.woods.framework.model.BaseModel;
import lombok.Data;

import java.util.Date;

@Data
public class ReservTb extends BaseModel {

  private Integer hospitalId;
  private Date reservTime;
  private Integer reservStatu;
  private Integer userId;
  private String phoneNum;
  private String email;
  private String gender;
  private String hospitalName;
  private String roleLevel;
  private String callName;
  private String symptom;
  private String volunPhone;
  private String volunEmail;
  private String volunNamed;

}
