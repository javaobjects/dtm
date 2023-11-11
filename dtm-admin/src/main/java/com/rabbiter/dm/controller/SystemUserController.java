package com.rabbiter.dm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rabbiter.dm.annotation.Log;
import com.rabbiter.dm.annotation.RequirePermission;
import com.rabbiter.dm.dto.ListQuery;
import com.rabbiter.dm.dto.Password;
import com.rabbiter.dm.entity.SystemUser;
import com.rabbiter.dm.exception.HttpException;
import com.rabbiter.dm.service.SystemUserService;
import com.rabbiter.dm.config.Constant;
import com.rabbiter.dm.utils.HttpCode;
import com.rabbiter.dm.utils.RedisUtil;
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
@RequestMapping("/system/user")
public class SystemUserController {
    @Autowired
    private SystemUserService systemUserService;
    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/list")
    @RequirePermission(permissions = {"system:user:list"})
    public Result<PageInfo<SystemUser>> list(@RequestBody ListQuery<SystemUser> listQuery) {
        //多对多查询，先获取符合条件的id，否则pageInfo中的数据会出错
        List<Long> ids = systemUserService.listIdFitCondition(listQuery.getEntity());
        PageHelper.startPage(listQuery.getPage(), listQuery.getRows());
        List<SystemUser> list = systemUserService.listByIds(ids);
        PageInfo<SystemUser> pageInfo = new PageInfo<>(list);
        return Result.<PageInfo<SystemUser>>ok().add(pageInfo);
    }

    @GetMapping("/query")
    @RequirePermission(permissions = {"system:user:query"})
    public Result<SystemUser> query(@RequestParam("id") Long id) {
        SystemUser systemUser = systemUserService.get(id).orElseThrow(() -> new HttpException(HttpCode.FAILED, "没有该用户"));
        return Result.<SystemUser>ok().add(systemUser);
    }

    @GetMapping("/delete")
    @RequirePermission(permissions = {"system:user:delete"})
    @Log("删除用户")
    public Result<?> delete(@RequestParam("id") Long id) {
        List<SystemUser> subordinates = systemUserService.listByLeaderId(id);
        if (subordinates.size() > 0) {
            throw new HttpException(HttpCode.FAILED, "该用户为其他人领导，无法删除");
        } else {
            systemUserService.delete(id);
            return Result.ok("删除成功");
        }

    }

    @PostMapping("/saveOrUpdate")
    @RequirePermission(permissions = {"system:user:save", "system:user:update"})
    @Log("添加修改用户")
    public Result<?> saveOrUpdate(@RequestBody @Validated SystemUser user) {
        Boolean validate = systemUserService.validateLoginName(user.getLoginName(),user.getId());
        if (!validate) {
            throw new HttpException(HttpCode.FAILED, "登录名称已存在");
        }
        systemUserService.saveOrUpdate(user);
        return Result.ok("操作成功");
    }

    @GetMapping("/resetPassword")
    @RequirePermission(permissions = {"system:user:update"})
    @Log("重置用户密码")
    public Result<?> resetPassword(@RequestParam("id") Long id) {
        systemUserService.changePassword(Constant.DEFAULT_PASSWORD, id);
        return Result.ok("密码重置成功");
    }

    @GetMapping("/listAll")
    @RequirePermission(permissions = {"system:user:list"})
    public Result<List<SystemUser>> list() {
        List<SystemUser> systemUserList = systemUserService.listAll();
        return Result.<List<SystemUser>>ok().add(systemUserList);
    }


    /**
     * 修改密码
     *
     * @param token    token
     * @param password 包括原密码，修改后的密码，再次输入的密码
     * @return 密码是否修改成功
     */
    @PostMapping("/change")
    public Result<?> validate(@RequestHeader(Constant.HEADER_TOKEN) String token, @RequestBody Password password) {
        if (!password.getCurrent1().equals(password.getCurrent2())) {
            throw new HttpException(HttpCode.FAILED, "两次密码输入不一致");
        }
        Long id = redisUtil.get(token);
        Boolean bool = systemUserService.validatePassword(password.getOldPassword(), id);
        if (bool) {
            systemUserService.changePassword(password.getCurrent1(), id);

            //移除token
            redisUtil.deleteToken(token);
            return Result.ok("密码修改成功,请重新登录");
        } else {
            throw new HttpException(HttpCode.FAILED, "原密码不正确");
        }
    }

    /**
     * 修改登录用户信息
     *
     * @param systemUser 修改的用户
     * @return 修改成功后将修改后的用户返回
     */
    @PostMapping("/update")
    public Result<SystemUser> update(@RequestBody SystemUser systemUser, @RequestHeader(Constant.HEADER_TOKEN) String token) {
        systemUser.setId(redisUtil.get(token));
        systemUserService.saveOrUpdate(systemUser);
        return Result.<SystemUser>ok("修改成功").add(systemUser);
    }

    @GetMapping("/changeIcon/{icon}")
    public Result<?> changeIcon(@PathVariable String icon , @RequestHeader(Constant.HEADER_TOKEN) String token) {
        systemUserService.changeIcon(icon, redisUtil.get(token));
        return Result.ok("修改成功");
    }
}
