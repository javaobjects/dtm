package com.rabbiter.dm.controller;

import com.github.pagehelper.PageInfo;
import com.rabbiter.dm.annotation.Log;
import com.rabbiter.dm.annotation.RequirePermission;
import com.rabbiter.dm.config.Constant;
import com.rabbiter.dm.dto.ListQuery;
import com.rabbiter.dm.entity.Leave;
import com.rabbiter.dm.entity.SystemUser;
import com.rabbiter.dm.service.LeaveService;
import com.rabbiter.dm.utils.RedisUtil;
import com.rabbiter.dm.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author xianxian
 * @date 2022-03-10
 */
@RestController
@RequestMapping("/leave")
public class LeaveController {
    @Autowired
    private LeaveService leaveService;
    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/saveOrUpdate")
    @RequirePermission(permissions = {"leave:save", "leave:update"})
    @Log
    public Result<?> save(@RequestBody @Validated Leave leave) {
        leaveService.saveOrUpdate(leave);
        return Result.ok("操作成功");
    }

    @PostMapping("/list")
    @RequirePermission(permissions = {"leave:list"})
    public Result<PageInfo<Leave>> list(@RequestBody ListQuery<Leave> listQuery,
                       @RequestHeader(Constant.HEADER_TOKEN) String token) {
        SystemUser user = redisUtil.exchange(token).get();
        PageInfo<Leave> pageInfo = leaveService.list(listQuery, user.getBuildingId());
        return Result.<PageInfo<Leave>>ok().add(pageInfo);
    }


    @GetMapping("/update/{id}")
    @RequirePermission(permissions = {"leave:update"})
    @Log
    public Result<?> update(@PathVariable Long id) {
        leaveService.update(id);
        return Result.ok("更新成功");
    }


    @GetMapping("/delete/{id}")
    @RequirePermission(permissions = {"leave:delete"})
    @Log
    public Result<?> delete(@PathVariable Long id) {
        leaveService.delete(id);
        return Result.ok("删除成功");
    }


    @GetMapping("/query/{id}")
    @RequirePermission(permissions = {"leave:query"})
    public Result<Leave> query(@PathVariable Long id) {
        Leave leave = leaveService.query(id);
        return Result.<Leave>ok().add(leave);
    }

}
