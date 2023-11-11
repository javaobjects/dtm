package com.rabbiter.dm.aop;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rabbiter.dm.annotation.Log;
import com.rabbiter.dm.entity.SystemLog;
import com.rabbiter.dm.exception.HttpException;
import com.rabbiter.dm.service.SystemLogService;
import com.rabbiter.dm.config.Constant;
import com.rabbiter.dm.utils.RedisUtil;
import com.rabbiter.dm.vo.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xianxian
 * @date 2021-12-29
 */
@Aspect
@Component
public class LogAop {

    private final RedisUtil redisUtil;
    private final SystemLogService systemLogService;

    public LogAop(RedisUtil redisUtil, SystemLogService systemLogService) {
        this.redisUtil = redisUtil;
        this.systemLogService = systemLogService;
    }

    @Pointcut("execution(* com.rabbiter.dm.controller.*.*(..))")
    public void pointcut1() {
    }

    /**
     * 记录系统日志
     * @param jp 。
     */
    @Around(value = "pointcut1()")
    public Object after(ProceedingJoinPoint jp) throws Throwable {
        MethodSignature signature = (MethodSignature)jp.getSignature();
        Method targetMethod = signature.getMethod();
        //方法上有Log 注解
        if (targetMethod.isAnnotationPresent(Log.class)) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            assert attributes != null;
            HttpServletRequest request = attributes.getRequest();

            //获取类名
            String clas = jp.getTarget().getClass().getSimpleName();

            //获取操作用户id
            String token = request.getHeader(Constant.HEADER_TOKEN);
            Long userId = redisUtil.get(token);

            //获取url
            String url = request.getRequestURI();

            //获取方法名
            String method = targetMethod.getName();

            //获取参数，并转成json
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            objectMapper.registerModule(new JavaTimeModule());

            Object[] args = jp.getArgs();
            Map<String, Object> param = new HashMap<>(2);
            for (Object arg : args) {
                String className = arg.getClass().getSimpleName();
                className = className.substring(0, 1).toLowerCase() + className.substring(1);
                param.put(className, arg);
            }

            //获取ip
            String ip = request.getRemoteAddr();

            //获取方法注解上的value
            Log l = targetMethod.getAnnotation(Log.class);

            SystemLog systemLog = new SystemLog();
            systemLog.setClas(clas);
            systemLog.setUserId(userId);
            systemLog.setIp(ip);
            systemLog.setUserId(userId);
            systemLog.setMethod(method);
            systemLog.setUrl(url);
            systemLog.setDescription(l.value());
            systemLog.setParam(objectMapper.writeValueAsString(param));
            Object result;
            //获取结果
            try {
                result = jp.proceed(args);
                systemLog.setResult(objectMapper.writeValueAsString(result));
            } catch (HttpException e) {
                Result res = new Result();
                res.setMsg(e.getMessage());
                res.setStatusCode(e.getCode());
                systemLog.setResult(objectMapper.writeValueAsString(res));
                throw e;
            } catch (Throwable t) {
                systemLog.setResult(objectMapper.writeValueAsString(t));
                throw t;
            } finally {
                systemLogService.save(systemLog);
            }
            return result;
        }
        return jp.proceed(jp.getArgs());
    }
}
