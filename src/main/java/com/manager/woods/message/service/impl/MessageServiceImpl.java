package com.manager.woods.message.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.manager.woods.framework.handler.MyException;
import com.manager.woods.framework.utils.DateUtils;
import com.manager.woods.message.dao.MessageTbDao;
import com.manager.woods.message.model.MessageTb;
import com.manager.woods.message.service.MessageService;
import com.manager.woods.reserv.dao.ReservTbDao;
import com.manager.woods.reserv.model.ReservTb;
import com.manager.woods.reserv.service.ReservService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageTbDao messageTbDao;


    @Override
    public int readMessage(Integer mesgId) {
        return messageTbDao.readMessage(mesgId);
    }

    @Override
    public List<MessageTb> findMessage(Integer userId) {
        return messageTbDao.findMessage(userId);
    }

    @Override
    public int deleteMessage(Integer mesgId) {
        return messageTbDao.deleteMessage(mesgId);
    }
}
