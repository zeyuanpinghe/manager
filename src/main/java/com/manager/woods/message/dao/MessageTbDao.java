package com.apricot.woods.message.dao;

import com.apricot.woods.message.model.MessageTb;
import com.apricot.woods.reserv.model.ReservTb;

import java.util.List;

public interface MessageTbDao {

    /**
     * 修改为已读消息
     * @param mesgId
     * @return
     */
    int readMessage(Integer mesgId);

    /**
     * 查询消息通知
     * @param userId
     * @return
     */
    List<MessageTb> findMessage(Integer userId);


    /**
     * 删除消息操作
     * @param mesgId
     * @return
     */
    int deleteMessage(Integer mesgId);

    /**
     * 添加消息通知
     * @param messageTb
     * @return
     */
    int addMessage(MessageTb messageTb);
}
