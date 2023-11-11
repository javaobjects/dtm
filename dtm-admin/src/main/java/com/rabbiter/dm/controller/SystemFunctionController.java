package com.rabbiter.dm.controller;

import com.rabbiter.dm.annotation.Log;
import com.rabbiter.dm.annotation.RequirePermission;
import com.rabbiter.dm.entity.SystemFunction;
import com.rabbiter.dm.exception.HttpException;
import com.rabbiter.dm.service.SystemFunctionService;
import com.rabbiter.dm.utils.HttpCode;
import com.rabbiter.dm.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xianxian
 * @date 2021-12-13
 */
@RestController
@RequestMapping("/system/function")
public class SystemFunctionController {
    @Autowired
    private SystemFunctionService systemFunctionService;

    @GetMapping("/list")
    @RequirePermission(permissions = {"system:function:list"})
    public Result<List<SystemFunction>> list() {
        List<SystemFunction> list = systemFunctionService.listTree();
        return Result.<List<SystemFunction>>ok().add(list);
    }

    @GetMapping("/delete")
    @RequirePermission(permissions = {"system:function:delete"})
    @Log("删除功能")
    public Result<?> delete(@RequestParam("id") Long id) {
        systemFunctionService.delete(id);
        return Result.ok("删除成功");
    }

    @PostMapping("/saveOrUpdate")
    @RequirePermission(permissions = {"system:function:save", "system:function:update"})
    @Log("添加修改功能")
    public Result<?> saveOrUpdate(@RequestBody @Validated SystemFunction function) {
        if (function.getId() == null) {
            systemFunctionService.save(function);
        } else {
            if (function.getId().equals(function.getParentId())) {
                throw new HttpException(HttpCode.FAILED, "父节点不能为自己");
            }
            systemFunctionService.update(function);
        }
        return Result.ok("操作成功");
    }

    @GetMapping("/query")
    @RequirePermission(permissions = {"system:function:query"})
    public Result<SystemFunction> query(@RequestParam("id") Long id) {
        SystemFunction function = systemFunctionService.query(id).orElseThrow(() -> new HttpException(HttpCode.FAILED, "菜单不存在"));
        return Result.<SystemFunction>ok().add(function);
    }
}
