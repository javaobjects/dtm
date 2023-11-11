package com.rabbiter.dm.service;

import com.rabbiter.dm.dao.SystemLogMapper;
import com.rabbiter.dm.entity.SystemLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xianxian
 * @date 2021-12-29
 */
@Service
public class SystemLogService {
    @Autowired
    private SystemLogMapper systemLogMapper;

    public void save(SystemLog systemLog) {
        systemLogMapper.save(systemLog);
    }

    public List<SystemLog> list(SystemLog systemLog) {
        return systemLogMapper.list(systemLog);
    }

    public SystemLog query(Long id) {
        return systemLogMapper.query(id);
    }

    public void deleteByMonth() {
        systemLogMapper.deleteByMonth();
    }
}
