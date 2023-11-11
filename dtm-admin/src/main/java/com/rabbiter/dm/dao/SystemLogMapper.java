package com.rabbiter.dm.dao;

import com.rabbiter.dm.entity.SystemLog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xianxian
 * @date 2021-12-29
 */
@Repository
public interface SystemLogMapper {

    /**
     * 保存日志
     * @param systemLog 日志
     */
    void save(SystemLog systemLog);

    /**
     * 查询列表
     * @param systemLog 查询条件
     * @return
     */
    List<SystemLog> list(SystemLog systemLog);

    /**
     * 查询详细
     * @param id .
     * @return 。
     */
    SystemLog query(Long id);

    /**
     * 删除一个月以前的日志
     */
    void deleteByMonth();
}
