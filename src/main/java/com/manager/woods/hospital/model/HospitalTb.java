package com.manager.woods.hospital.model;

import lombok.Data;

@Data
public class HospitalTb {

  private long id;
  private String hospitalname;
  private String phonepath;
  private String therapyid;
  private String hospdesc;
  private java.sql.Timestamp createtime;
  private java.sql.Timestamp updatetime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getHospitalname() {
    return hospitalname;
  }

  public void setHospitalname(String hospitalname) {
    this.hospitalname = hospitalname;
  }


  public String getPhonepath() {
    return phonepath;
  }

  public void setPhonepath(String phonepath) {
    this.phonepath = phonepath;
  }


  public String getTherapyid() {
    return therapyid;
  }

  public void setTherapyid(String therapyid) {
    this.therapyid = therapyid;
  }


  public String getHospdesc() {
    return hospdesc;
  }

  public void setHospdesc(String hospdesc) {
    this.hospdesc = hospdesc;
  }


  public java.sql.Timestamp getCreatetime() {
    return createtime;
  }

  public void setCreatetime(java.sql.Timestamp createtime) {
    this.createtime = createtime;
  }


  public java.sql.Timestamp getUpdatetime() {
    return updatetime;
  }

  public void setUpdatetime(java.sql.Timestamp updatetime) {
    this.updatetime = updatetime;
  }

}
