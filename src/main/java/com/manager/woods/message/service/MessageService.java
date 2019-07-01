package com.manager.woods.message.service;

import com.manager.woods.message.model.MessageTb;
import com.manager.woods.reserv.model.ReservTb;

import java.util.List;

public interface MessageService {

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
}
