package com.rabbiter.dm.controller;

import com.rabbiter.dm.entity.SystemUser;
import com.rabbiter.dm.exception.HttpException;
import com.rabbiter.dm.service.SystemUserService;
import com.rabbiter.dm.utils.HttpCode;
import com.rabbiter.dm.utils.MD5Util;
import com.rabbiter.dm.utils.RedisUtil;
import com.rabbiter.dm.vo.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.rabbiter.dm.config.Constant.HEADER_TOKEN;

/**
 * @author xianxian
 * @date 2021-12-08
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    private final RedisUtil redisUtil;
    private final SystemUserService systemUserService;

    public LoginController(RedisUtil redisUtil, SystemUserService systemUserService) {
        this.redisUtil = redisUtil;
        this.systemUserService = systemUserService;
    }

    @PostMapping("/login")
    public Result<String> login(SystemUser user, @RequestParam(value = "redirectUrl") String redirectUrl) {
        List<SystemUser> userList = systemUserService.listUserByLoginName(user.getLoginName());
        if (userList.size() == 0) {
            throw new HttpException(HttpCode.LOGIN_FAILED, "没有此用户");
        } else if (userList.size() > 1) {
            throw new HttpException(HttpCode.LOGIN_FAILED, "存在多个登录名，请联系管理员");
        } else {
            SystemUser systemUser = userList.get(0);
            if (systemUser.getPassword().equals(MD5Util.md5(user.getPassword()))) {
                String token = UUID.randomUUID().toString();
                redisUtil.put(token, systemUser.getId());
                return Result.<String>ok().add(redirectUrl + "#/token=" + token);
            } else {
                throw new HttpException(HttpCode.LOGIN_FAILED, "登陆失败，密码错误");
            }
        }

    }

    @GetMapping("/logout")
    public Result<?> logout(@RequestHeader(HEADER_TOKEN) String token) {
        if (redisUtil.hasToken(token)) {
            redisUtil.deleteToken(token);
        }
        return Result.ok();
    }
}
