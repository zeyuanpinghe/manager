package com.manager.woods.reserv.controller;

import com.manager.woods.framework.common.Result;
import com.manager.woods.framework.handler.MyException;
import com.manager.woods.framework.service.BaseService;
import com.manager.woods.framework.utils.HttpRequestUtil;
import com.manager.woods.framework.utils.ResultUtil;
import com.manager.woods.reserv.model.ReservTb;
import com.manager.woods.reserv.service.ReservService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RequestMapping("/reserv")
@RestController
public class ReservController{

    private static final Logger logger = LoggerFactory.getLogger(ReservController.class);

    @Autowired
    private BaseService baseService;

    @Autowired
    private ReservService reservService;

    /**
     * 预约接口
     * @return
     */
    @RequestMapping(value="appointment", method = RequestMethod.POST)
    public Result<String> saveOrder(
            HttpServletRequest httpRequest){
        Map<String,String> requestMap = HttpRequestUtil.commonHttpRequestParamConvert(httpRequest);
        logger.info("receive AppCallHttpRequest requestMap:{}", requestMap);
        String token = requestMap.get("token");
        HttpRequestUtil.checkParam(token,"token");

        String gender = requestMap.get("gender");
        HttpRequestUtil.checkParam(gender,"gender");

        String reservTime = requestMap.get("reservTime");
        HttpRequestUtil.checkParam(reservTime,"reservTime");

        String hospitalId = requestMap.get("hospitalId");
        HttpRequestUtil.checkParam(hospitalId,"hospitalId");

        String callName = requestMap.get("callName");
        HttpRequestUtil.checkParam(callName,"callName");

        String phoneNum = requestMap.get("phoneNum");

        String email = requestMap.get("email");

        String symptom = requestMap.get("symptom");

        if(reservService.saveReserv(baseService.verityTokenAndgetUserId(token),gender,reservTime,Integer.valueOf(hospitalId),phoneNum,email,callName,symptom) < 1){
            throw new MyException("103","Reservation failed!");
        }
        return ResultUtil.ok(null);
    }

    /**
     * 查询当前订单接口
     * @return
     */
    @RequestMapping(value="currentOrder", method = RequestMethod.POST)
    public Result<ReservTb> findCurrentOrder(HttpServletRequest httpRequest){
            Map<String,String> requestMap = HttpRequestUtil.commonHttpRequestParamConvert(httpRequest);
           logger.info("receive AppCallHttpRequest requestMap:{}", requestMap);
            String token = requestMap.get("token");
            HttpRequestUtil.checkParam(token,"token");

        return ResultUtil.ok(reservService.currentReservByUserId(baseService.verityTokenAndgetUserId(token)));
    }

    /**
     * 查询所有订单接口
     * @return
     */
    @RequestMapping(value="allOrder", method = RequestMethod.POST)
    public Result<List> findAllOrder(HttpServletRequest httpRequest){
        Map<String,String> requestMap = HttpRequestUtil.commonHttpRequestParamConvert(httpRequest);
        logger.info("receive AppCallHttpRequest requestMap:{}", requestMap);
        String token = requestMap.get("token");
        HttpRequestUtil.checkParam(token,"token");
        return ResultUtil.ok(reservService.findReservByUserId(baseService.verityTokenAndgetUserId(token)));
    }

    /**
     * 订单更新接口
     * @param httpRequest
     * @return
     */
    @RequestMapping(value="update", method = RequestMethod.POST)
    public Result<String> updateOrder(HttpServletRequest httpRequest){
        Map<String,String> requestMap = HttpRequestUtil.commonHttpRequestParamConvert(httpRequest);
        logger.info("receive AppCallHttpRequest requestMap:{}", requestMap);
        String token = requestMap.get("token");
        HttpRequestUtil.checkParam(token,"token");

        String gender = requestMap.get("gender");
        HttpRequestUtil.checkParam(gender,"gender");

        String reservTime = requestMap.get("reservTime");
        HttpRequestUtil.checkParam(reservTime,"reservTime");

        String hospitalId = requestMap.get("hospitalId");
        HttpRequestUtil.checkParam(hospitalId,"hospitalId");

        String reservId = requestMap.get("reservId");
        HttpRequestUtil.checkParam(reservId,"reservId");

        String callName = requestMap.get("callName");
        HttpRequestUtil.checkParam(callName,"callName");

        String phoneNum = requestMap.get("phoneNum");

        String email = requestMap.get("email");

        String symptom = requestMap.get("symptom");

        if(reservService.updateReserv(baseService.verityTokenAndgetUserId(token),gender,reservTime,Integer.valueOf(hospitalId),phoneNum,email,Integer.valueOf(reservId),callName,symptom) < 1){
            throw new MyException("104","The change failed!");
        }
        return ResultUtil.ok(null);
    }

    /**
     * 预约接口
     * @return
     */
    @RequestMapping(value="match", method = RequestMethod.POST)
    public Result<String> matchOrder(
            HttpServletRequest httpRequest){
        Map<String,String> requestMap = HttpRequestUtil.commonHttpRequestParamConvert(httpRequest);
        logger.info("receive AppCallHttpRequest requestMap:{}", requestMap);
        String token = requestMap.get("token");
        HttpRequestUtil.checkParam(token,"token");

        String reservId = requestMap.get("reservId");
        HttpRequestUtil.checkParam(reservId,"reservId");

        String volunId = requestMap.get("volunId");
        HttpRequestUtil.checkParam(volunId,"volunId");

        String department = requestMap.get("department");
        reservService.matchReserv(baseService.verityTokenAndgetUserId(token), Integer.valueOf(reservId), Integer.valueOf(volunId), department);

        return ResultUtil.ok(null);
    }

    /**
     * 等待匹配的有效订单
     * @param httpRequest
     * @return
     */
    @RequestMapping(value="commitOrder", method = RequestMethod.POST)
    public Result<List> commitOrder(
            HttpServletRequest httpRequest){
        Map<String,String> requestMap = HttpRequestUtil.commonHttpRequestParamConvert(httpRequest);
        logger.info("receive AppCallHttpRequest requestMap:{}", requestMap);
        String token = requestMap.get("token");
        HttpRequestUtil.checkParam(token,"token");

        String pageNow = requestMap.get("pageNow");

        String pageSize = requestMap.get("pageSize");

        baseService.verityTokenAndgetUserId(token);
        List<ReservTb> reservTbList = reservService.getCommitReserv(pageNow,pageSize);
        int count = reservService.findALLCommitOrder();
        return ResultUtil.ok(reservTbList,count);
    }
}
