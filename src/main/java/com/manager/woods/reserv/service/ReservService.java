package com.manager.woods.reserv.service;

import com.manager.woods.reserv.model.ReservTb;

import java.util.List;

public interface ReservService {

    /**
     * 保存预约信息
     * @param userId
     * @param gender
     * @param reservTime
     * @param hospitalId
     * @param phoneNum
     * @param email
     * @return
     */
    int saveReserv(Integer userId,String gender,String reservTime,int hospitalId,String phoneNum,String email,String callName,String symptom);

    /**
     * 查询订单记录
     * @param userId
     * @return
     */
    List<ReservTb> findReservByUserId(Integer userId);

    /**
     * 查询当前预约订单
     * @param userId
     * @return
     */
    ReservTb currentReservByUserId(Integer userId);

    /**
     * 更新预约信息
     * @param userId
     * @param gender
     * @param reservTime
     * @param hospitalId
     * @param phoneNum
     * @param email
     * @param reservId
     * @return
     */
    int updateReserv(Integer userId,String gender,String reservTime,int hospitalId,String phoneNum,String email,Integer reservId,String callName,String symptom);

    /**
     * 志愿者匹配操作
     * @param updateId 当前操作者ID
     * @param reservId  订单ID
     * @param volunId 志愿者ID
     * @param department 预约部门
     * @return
     */
    int matchReserv(Integer updateId,Integer reservId,Integer volunId,String department);

    /**
     * 查询未匹配的有效的所有订单
     * @return
     */
    List<ReservTb> getCommitReserv(String pageNow, String pageSize);

    /**
     * 查找所有未匹配订单的总数
     * @return
     */
    int findALLCommitOrder();

    /**
     * 根据志愿者ID查询有效未完成订单
     * @return
     */
    List<ReservTb> findMatchOrderByVold(Integer volId);
}
