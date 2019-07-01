package com.manager.woods.message.controller;

import com.manager.woods.framework.common.Result;
import com.manager.woods.framework.handler.MyException;
import com.manager.woods.framework.service.BaseService;
import com.manager.woods.framework.utils.HttpRequestUtil;
import com.manager.woods.framework.utils.ResultUtil;
import com.manager.woods.message.model.MessageTb;
import com.manager.woods.message.service.MessageService;
import com.manager.woods.reserv.model.ReservTb;
import com.manager.woods.reserv.service.ReservService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RequestMapping("/notify")
@RestController
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private BaseService baseService;

    @Autowired
    private MessageService messageService;

    /**
     * 消息已读接口
     * @return
     */
    @RequestMapping(value="read", method = RequestMethod.POST)
    public Result<String> readMessage(
            HttpServletRequest httpRequest){
        Map<String,String> requestMap = HttpRequestUtil.commonHttpRequestParamConvert(httpRequest);
        logger.info("receive AppCallHttpRequest requestMap:{}", requestMap);
        String token = requestMap.get("token");
        HttpRequestUtil.checkParam(token,"token");

        String mesgId = requestMap.get("mesgId");
        HttpRequestUtil.checkParam(mesgId,"mesgId");
        //权限校验
        baseService.verityTokenAndgetUserId(token);
        //修改为已读标识
        messageService.readMessage(Integer.valueOf(mesgId));

        return ResultUtil.ok(null);
    }

    /**
     * 查询所有通知消息
     * @return
     */
    @RequestMapping(value="message", method = RequestMethod.POST)
    public Result<List> findMessage(HttpServletRequest httpRequest){
            Map<String,String> requestMap = HttpRequestUtil.commonHttpRequestParamConvert(httpRequest);
        logger.info("receive AppCallHttpRequest requestMap:{}", requestMap);
            String token = requestMap.get("token");
            HttpRequestUtil.checkParam(token,"token");

        return ResultUtil.ok(messageService.findMessage(baseService.verityTokenAndgetUserId(token)));
    }

    /**
     * 消息删除接口
     * @return
     */
    @RequestMapping(value="remove", method = RequestMethod.POST)
    public Result<String> removeMessage(
            HttpServletRequest httpRequest){
        Map<String,String> requestMap = HttpRequestUtil.commonHttpRequestParamConvert(httpRequest);
        logger.info("receive AppCallHttpRequest requestMap:{}", requestMap);
        String token = requestMap.get("token");
        HttpRequestUtil.checkParam(token,"token");

        String mesgId = requestMap.get("mesgId");
        HttpRequestUtil.checkParam(mesgId,"mesgId");
        //权限校验
        baseService.verityTokenAndgetUserId(token);
        //消息删除
        messageService.deleteMessage(Integer.valueOf(mesgId));

        return ResultUtil.ok(null);
    }
}
