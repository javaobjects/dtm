package com.rabbiter.dm.interceptor;

import com.rabbiter.dm.config.Constant;
import com.rabbiter.dm.exception.HttpException;
import com.rabbiter.dm.utils.HttpCode;
import com.rabbiter.dm.utils.RedisUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 * @author xianxian
 * @date 2021-12-12
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    private final RedisUtil redisUtil;

    public LoginInterceptor(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(Constant.HEADER_TOKEN);
        if (token == null) {
            throw new HttpException(HttpCode.NOT_LOGIN, "未登录");
        } else {
            if (!redisUtil.hasToken(token)) {
                throw new HttpException(HttpCode.LOGIN_EXPIRED, "登录已过期");
            } else {
                redisUtil.exchange(token).orElseThrow(() -> new HttpException(HttpCode.LOGIN_EXPIRED, "登录用户不存在"));
                redisUtil.setExpireTime(token);
                return true;
            }
        }

    }
}
