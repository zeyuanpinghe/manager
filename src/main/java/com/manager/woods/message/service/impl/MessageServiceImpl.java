package com.apricot.woods.message.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.apricot.woods.framework.handler.MyException;
import com.apricot.woods.framework.utils.DateUtils;
import com.apricot.woods.message.dao.MessageTbDao;
import com.apricot.woods.message.model.MessageTb;
import com.apricot.woods.message.service.MessageService;
import com.apricot.woods.reserv.dao.ReservTbDao;
import com.apricot.woods.reserv.model.ReservTb;
import com.apricot.woods.reserv.service.ReservService;
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
