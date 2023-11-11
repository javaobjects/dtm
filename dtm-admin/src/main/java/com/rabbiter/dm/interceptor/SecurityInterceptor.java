package com.rabbiter.dm.interceptor;

import com.rabbiter.dm.annotation.RequirePermission;
import com.rabbiter.dm.entity.SystemRole;
import com.rabbiter.dm.exception.HttpException;
import com.rabbiter.dm.service.SystemFunctionService;
import com.rabbiter.dm.service.SystemRoleService;
import com.rabbiter.dm.config.Constant;
import com.rabbiter.dm.utils.HttpCode;
import com.rabbiter.dm.utils.Match;
import com.rabbiter.dm.utils.RedisUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 权限拦截器
 * @author xianxian
 * @date 2021-12-08
 */
@Component
public class SecurityInterceptor implements HandlerInterceptor {
    private final RedisUtil redisUtil;
    private final SystemFunctionService systemFunctionService;
    private final SystemRoleService systemRoleService;
    private static final Map<Match, Validate> VALIDATE_MAP = new HashMap<>();

    static {
        VALIDATE_MAP.put(Match.HAS_ANY, (userPermission, methodPermission) -> {
            for (String up : userPermission) {
                for (String mp : methodPermission) {
                    if (up.equalsIgnoreCase(mp)) {
                        return true;
                    }
                }
            }
            return false;
        });
        VALIDATE_MAP.put(Match.HAS_ALL, (userPermission, methodPermission) -> {
            int vote = 0;
            for (String up : userPermission) {
                for (String mp : methodPermission) {
                    if (up.equalsIgnoreCase(mp)) {
                        vote++;
                    }
                }
            }
            return vote == methodPermission.length;
        });
    }

    public SecurityInterceptor(RedisUtil redisUtil, SystemFunctionService systemFunctionService, SystemRoleService systemRoleService) {
        this.redisUtil = redisUtil;
        this.systemFunctionService = systemFunctionService;
        this.systemRoleService = systemRoleService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //获取请求的方法
        HandlerMethod handlerMethod;
        if (handler instanceof HandlerMethod) {
            handlerMethod = (HandlerMethod) handler;
        } else {
            //404
            return true;
        }
        Method method = handlerMethod.getMethod();
        //获取请求方法所需的权限
        String[] requiredPermissions;
        Match match;
        if (method.isAnnotationPresent(RequirePermission.class)) {
            RequirePermission hasPermission = method.getAnnotation(RequirePermission.class);
            requiredPermissions = hasPermission.permissions();
            match = hasPermission.matchType();
        } else {
            //方法不需要权限（无 RequirePermission 注解）
            return true;
        }

        String token = request.getHeader(Constant.HEADER_TOKEN);

        Long id = redisUtil.get(token);

        //获取该用户的权限
        List<SystemRole> roleList = systemRoleService.listByUserId(id);
        Set<String> permissions;
        if (roleList.size() == 0) {
            permissions = new HashSet<>();
        } else {
            permissions = systemFunctionService.getPermission(roleList);
        }

        //验证权限
        if (VALIDATE_MAP.get(match).validate(permissions, requiredPermissions)) {
            return true;
        }
        //权限验证失败
        throw new HttpException(HttpCode.HAS_NO_PERMISSIONS, "没有权限，请联系管理员");
    }


    private interface Validate {
        /**
         * 验证权限
         * @param userPermission 用户拥有的权限
         * @param methodPermission 方法需要的权限
         * @return 是否通过
         */
        Boolean validate(Set<String> userPermission, String[] methodPermission);
    }
}
