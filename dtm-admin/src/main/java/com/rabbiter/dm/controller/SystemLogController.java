package com.rabbiter.dm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rabbiter.dm.annotation.RequirePermission;
import com.rabbiter.dm.dto.ListQuery;
import com.rabbiter.dm.entity.SystemLog;
import com.rabbiter.dm.service.SystemLogService;
import com.rabbiter.dm.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xianxian
 * @date 2021-12-29
 */
@RestController
@RequestMapping("/system/log")
public class SystemLogController {

    @Autowired
    private SystemLogService systemLogService;


    @PostMapping("/list")
    @RequirePermission(permissions = {"system:log:list"})
    public Result<PageInfo<SystemLog>> list(@RequestBody ListQuery<SystemLog> listQuery) {
        PageHelper.startPage(listQuery.getPage(), listQuery.getRows());
        List<SystemLog> systemLogList = systemLogService.list(listQuery.getEntity());
        PageInfo<SystemLog> pageInfo = new PageInfo<>(systemLogList);
        return Result.<PageInfo<SystemLog>>ok().add(pageInfo);
    }

    @GetMapping("/query")
    @RequirePermission(permissions = {"system:log:query"})
    public Result<SystemLog> query(@RequestParam("id") Long id) {
        SystemLog systemLog = systemLogService.query(id);
        return Result.<SystemLog>ok().add(systemLog);
    }
}
