package com.apricot.woods.reserv.dao;

import com.apricot.woods.reserv.model.ReservTb;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ReservTbDao {

    /**
     * 保存预约信息
     * @param reservTb
     * @return
     */
    int saveReserv(ReservTb reservTb);

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
     * @param reservTb
     *
     * @return
     */
    int updateReserv(ReservTb reservTb);

    /**
     * 根据预约时间和志愿者ID查询当前有效订单
     * @param volMap
     * @return
     */
    List<ReservTb> selectReservByTimeAndVolId(Map volMap);

    /**
     * 通过订单ID更新志愿者ID
     * @param reservId
     * @param volunId
     * @return
     */
    Integer updateMatchById(Integer reservId,Integer volunId);

    /**
     * 通过ID查询订单
     * @param reservId
     * @return
     */
    ReservTb selectReservById(Integer reservId);

    /**
     *  分页查找未匹配订单
     * @param paramMap
     */
    List<ReservTb> getCommitReserv(Map paramMap);

    /**
     * 获取未匹配有效订单总数
     * @return
     */
    int findALLCommitOrder();

    /**
     * 根据志愿者ID查询有效未完成订单
     * @return
     */
    List<ReservTb> findMatchOrderByVold(Integer volId);
}
