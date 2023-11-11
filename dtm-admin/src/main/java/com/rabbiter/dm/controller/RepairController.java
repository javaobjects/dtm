package com.rabbiter.dm.controller;

import com.github.pagehelper.PageInfo;
import com.rabbiter.dm.annotation.Log;
import com.rabbiter.dm.annotation.RequirePermission;
import com.rabbiter.dm.config.Constant;
import com.rabbiter.dm.dto.ListQuery;
import com.rabbiter.dm.entity.Repair;
import com.rabbiter.dm.entity.SystemUser;
import com.rabbiter.dm.service.RepairService;
import com.rabbiter.dm.utils.RedisUtil;
import com.rabbiter.dm.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author XiongXinxin
 * @date 2022-03-13
 */
@RestController
@RequestMapping("/repair")
public class RepairController {
    @Autowired
    private RepairService repairService;
    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/saveOrUpdate")
    @RequirePermission(permissions = {"repair:update", "repair:save"})
    @Log
    public Result<?> save(@RequestBody @Validated Repair repair) {
        if (repair.getId() == null) {
            repairService.save(repair);
        } else {
            repairService.update(repair);
        }
        return Result.ok("添加成功");
    }

    @GetMapping("/updateStatus/{id}")
    @RequirePermission(permissions = {"repair:update"})
    @Log
    public Result<?> updateStatus(@PathVariable Long id) {
        repairService.updateStatus(id);
        return Result.ok("修改成功");
    }

    @PostMapping("/list")
    @RequirePermission(permissions = {"repair:list"})
    public Result<PageInfo<Repair>> list(@RequestBody ListQuery<Repair> listQuery, @RequestHeader(Constant.HEADER_TOKEN) String token) {
        SystemUser user = redisUtil.exchange(token).get();
        PageInfo<Repair> pageInfo = repairService.list(listQuery, user.getBuildingId());
        return Result.<PageInfo<Repair>>ok().add(pageInfo);
    }

    @GetMapping("/query/{id}")
    @RequirePermission(permissions = {"repair:query"})
    public Result<Repair> query(@PathVariable Long id) {
        Repair repair = repairService.query(id);
        return Result.<Repair>ok().add(repair);
    }

    @GetMapping("/delete/{id}")
    @RequirePermission(permissions = {"repair:delete"})
    @Log
    public Result<?> delete(@PathVariable Long id) {
        repairService.delete(id);
        return Result.ok("删除成功");
    }
}
