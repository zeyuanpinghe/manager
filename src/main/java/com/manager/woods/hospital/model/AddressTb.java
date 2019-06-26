package com.apricot.woods.hospital.model;

import lombok.Data;

@Data
public class AddressTb {

  private long id;
  private long hostpitalid;
  private String address;
  private String bus;
  private String metra;
  private java.sql.Timestamp createtime;
  private java.sql.Timestamp updatetime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getHostpitalid() {
    return hostpitalid;
  }

  public void setHostpitalid(long hostpitalid) {
    this.hostpitalid = hostpitalid;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public String getBus() {
    return bus;
  }

  public void setBus(String bus) {
    this.bus = bus;
  }


  public String getMetra() {
    return metra;
  }

  public void setMetra(String metra) {
    this.metra = metra;
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
