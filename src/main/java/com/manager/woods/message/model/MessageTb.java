package com.apricot.woods.message.model;

import com.apricot.woods.framework.model.BaseModel;
import lombok.Data;

import java.util.Date;

@Data
public class MessageTb extends BaseModel {
    private Integer hospitalId;
    private Date reservTime;
    private Integer reservStatu;
    private Integer userId;
    private String illPhoneNum;
    private String illEmail;
    private String illGender;
    private String hospitalName;
    private String illCallName;
    private String symptom;
    private String volCallName;
    private String volPhoneNum;
    private String volEmail;
    private String mesgStatu;
    private Integer reservId;
    private Integer mesgId;
}
