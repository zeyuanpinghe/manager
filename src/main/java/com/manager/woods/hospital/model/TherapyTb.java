package com.apricot.woods.hospital.model;


import lombok.Data;

@Data
public class TherapyTb {

  private long id;
  private String thername;
  private String introall;
  private String introbrief;
  private String therdesc;
  private java.sql.Timestamp createtime;
  private java.sql.Timestamp updatetime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getThername() {
    return thername;
  }

  public void setThername(String thername) {
    this.thername = thername;
  }


  public String getIntroall() {
    return introall;
  }

  public void setIntroall(String introall) {
    this.introall = introall;
  }


  public String getIntrobrief() {
    return introbrief;
  }

  public void setIntrobrief(String introbrief) {
    this.introbrief = introbrief;
  }


  public String getTherdesc() {
    return therdesc;
  }

  public void setTherdesc(String therdesc) {
    this.therdesc = therdesc;
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
