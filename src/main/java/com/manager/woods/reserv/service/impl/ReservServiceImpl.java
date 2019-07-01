package com.manager.woods.reserv.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.manager.woods.framework.handler.MyException;
import com.manager.woods.framework.utils.DateUtils;
import com.manager.woods.framework.utils.PageAndSizeUtil;
import com.manager.woods.message.dao.MessageTbDao;
import com.manager.woods.message.model.MessageTb;
import com.manager.woods.reserv.dao.ReservTbDao;
import com.manager.woods.reserv.model.ReservTb;
import com.manager.woods.reserv.service.ReservService;
import com.manager.woods.user.dao.UserTbDao;
import com.manager.woods.user.model.UserTb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReservServiceImpl implements ReservService {

    @Autowired
    private ReservTbDao reservTbDao;

    @Autowired
    private MessageTbDao messageTbDao;

    @Autowired
    private UserTbDao userTbDao;


    @Transactional
    @Override
    public int saveReserv(Integer userId,String gender,String reservTime,int hospitalId,String phoneNum,String email,String callName,String symptom) {
        if(userId == null){
            throw new MyException("101","Illegal access!");
        }

        if(StringUtils.isEmpty(phoneNum) && StringUtils.isEmpty(email)){
            throw new MyException("102","Lack of contact information!");
        }
        if(reservTbDao.currentReservByUserId(userId) != null){
            throw new MyException("104","Do not submit any unfinished orders again!");
        }
        ReservTb reservTb = getReservTb(userId, gender, reservTime, hospitalId, phoneNum, email,callName,symptom);
        Integer reservId = reservTbDao.saveReserv(reservTb);
        if (reservId < 0)
            return 0;
        MessageTb messageTb = new MessageTb();
        messageTb.setReservId(reservTb.getId());
        messageTb.setUserId(userId);
        messageTb.setReservStatu(1);//首次插入该订单的消息通知
        return messageTbDao.addMessage(messageTb);
    }

    @Override
    public List<ReservTb> findReservByUserId(Integer userId) {
        return reservTbDao.findReservByUserId(userId);
    }

    @Override
    public ReservTb currentReservByUserId(Integer userId)
    {
        return reservTbDao.currentReservByUserId(userId);
    }

    @Transactional
    @Override
    public int updateReserv(Integer userId,String gender,String reservTime,int hospitalId,String phoneNum,String email,Integer reservId,String callName,String symptom){
        if(StringUtils.isEmpty(phoneNum) && StringUtils.isEmpty(email)){
            throw new MyException("102","Lack of contact information!");
        }

        ReservTb reservTb = getReservTb(userId, gender, reservTime, hospitalId, phoneNum, email, callName,symptom);
        if(reservTb != null){
            reservTb.setId(reservId);
        }
        return reservTbDao.updateReserv(reservTb);
    }

    @Override
    public int matchReserv(Integer updateId, Integer reservId, Integer volunId, String department) {
        if(reservId == null || volunId == null){
            throw new MyException("102","Invalid order or volunteer information");
        }

        ReservTb reservTb = reservTbDao.selectReservById(reservId);
        if(reservTb == null){
            throw new MyException("103","Invalid order information!");
        }
        if(2 == reservTb.getReservStatu()){
            throw new MyException("104","order have been matched!");
        }
        if(3 == reservTb.getReservStatu()){
            throw new MyException("105","order have been completed!");
        }
        UserTb userTb = userTbDao.selectVolunById(volunId);
        if(userTb == null){
            throw new MyException("106","Invalid volunteer information!");
        }
        Map volMap = new HashMap();
        volMap.put("volunId",volunId);
        volMap.put("reservTime",reservTb.getReservTime());
        List<ReservTb> matchReservList = reservTbDao.selectReservByTimeAndVolId(volMap);
        if(matchReservList!=null && matchReservList.size()>0)
            throw new MyException("107","Volunteers have been booked for that time!");
        //匹配志愿者,(在预约单上更新志愿者ID)
        Integer matSum = reservTbDao.updateMatchById(reservId,volunId);
        //消息通知中插入匹配成功消息数据
        MessageTb messageTb = new MessageTb();
        messageTb.setReservId(reservTb.getId());
        messageTb.setUserId(reservTb.getUserId());
        messageTb.setReservStatu(2);//匹配插入该订单的消息通知
        Integer messageSum = messageTbDao.addMessage(messageTb);

        if(matSum < 1 || messageSum < 1){
            throw new MyException("108","match fail!");
        }

        return matSum;
    }

    @Override
    public List<ReservTb> getCommitReserv(String pageNow, String pageSize) {

        Map paramMap = PageAndSizeUtil.getPageMap(pageNow,pageSize,new HashMap());
        //查询当前页面数据
        List<ReservTb> reservTbList = reservTbDao.getCommitReserv(paramMap);

        if(CollectionUtils.isEmpty(reservTbList)){
            return null;
        }
        return reservTbList;
    }

    @Override
    public int findALLCommitOrder() {
        return reservTbDao.findALLCommitOrder();
    }

    @Override
    public List<ReservTb> findMatchOrderByVold(Integer volId) {
        return reservTbDao.findMatchOrderByVold(volId);
    }

    /**
     * 组装预约信息
     * @param userId
     * @param gender
     * @param reservTime
     * @param hospitalId
     * @param phoneNum
     * @param email
     * @return
     */
    private  ReservTb getReservTb(Integer userId,String gender,String reservTime,int hospitalId,String phoneNum,String email,String callName,String symptom){
        ReservTb reservTb = new ReservTb();
        reservTb.setUserId(userId);
        reservTb.setGender(gender);
        reservTb.setReservTime(DateUtils.strToDateLong(reservTime));
        reservTb.setHospitalId(hospitalId);
        reservTb.setPhoneNum(phoneNum);
        reservTb.setEmail(email);
        reservTb.setCallName(callName);
        reservTb.setSymptom(symptom);
        return reservTb;
    }

}
