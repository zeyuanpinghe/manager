

package com.manager.woods.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.manager.woods.common.utils.PageUtils;
import com.manager.woods.sys.entity.SysLogEntity;

import java.util.Map;


/**
 * 系统日志
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysLogService extends IService<SysLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

}
