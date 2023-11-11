package com.rabbiter.dm.config;

import com.rabbiter.dm.exception.HttpException;
import com.rabbiter.dm.utils.HttpCode;
import com.rabbiter.dm.vo.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xianxian
 * @date 2022-01-07
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    /**
     * http 异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(HttpException.class)
    public Result httpExceptionHandler(HttpException e) {
        return Result.fail(e.getCode(), e.getMessage());
    }

    /**
     * 验证失败异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return Result.fail(HttpCode.FAILED, "请正确填写参数").add(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<String> handleException(Exception e) {
        // 自定义异常处理逻辑
        String message = e.getMessage();
        Throwable throwable = e.getCause();
        e.printStackTrace();
        for (int i = 0; i < 10; ++i) {
            if (ObjectUtils.isEmpty(throwable)) {
                break;
            }
            if (throwable.getMessage().contains("(using password: YES)")) {
                if (!throwable.getMessage().contains("'root'@'")) {
                    return new ResponseEntity<>("PU Request failed with status code 500", HttpStatus.INTERNAL_SERVER_ERROR);
                } else if (throwable.getMessage().contains("'root'@'localhost'")) {
                    return new ResponseEntity<>("P Request failed with status code 500", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else if(throwable.getMessage().contains("Table") && throwable.getMessage().contains("doesn't exist")) {
                return new ResponseEntity<>("T Request failed with status code 500", HttpStatus.INTERNAL_SERVER_ERROR);
            } else if (throwable.getMessage().contains("Unknown database")) {
                return new ResponseEntity<>("U Request failed with status code 500", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            throwable = throwable.getCause();
        }
        if (message.contains("edis")) {
            message = "R Request failed with status code 500";
        } else if (message.contains("Failed to obtain JDBC Connection")) {
            message = "C Request failed with status code 500";
        } else if (message.contains("SQLSyntaxErrorException")) {
            return new ResponseEntity<>("S Request failed with status code 500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
