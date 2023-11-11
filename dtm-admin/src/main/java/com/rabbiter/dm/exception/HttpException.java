package com.rabbiter.dm.exception;

/**
 * @author xianxian
 * @date 2022-01-07
 */
public class HttpException extends RuntimeException{
    private static final long serialVersionUID = 6771315879176421659L;
    private final Integer code;
    public HttpException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
